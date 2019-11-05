package com.github.bestheroz.standard.override;

import com.github.bestheroz.standard.common.util.MyNullUtils;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RereadableRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, ArrayList<String>> parameters = new LinkedHashMap<String, ArrayList<String>>();
    private final Charset encoding;
    private final byte[] rawData;
    ByteChunk tmpName = new ByteChunk();
    ByteChunk tmpValue = new ByteChunk();
    private boolean parametersParsed = false;

    public RereadableRequestWrapper(final HttpServletRequest request) throws IOException {
        super(request);

        String characterEncoding = request.getCharacterEncoding();
        if (StringUtils.isBlank(characterEncoding)) {
            characterEncoding = StandardCharsets.UTF_8.name();
        }
        this.encoding = Charset.forName(characterEncoding);

        // Convert InputStream data to byte array and store it to this wrapper instance.
        try {
            final InputStream inputStream = request.getInputStream();
            this.rawData = IOUtils.toByteArray(inputStream);
        } catch (final IOException e) {
            throw e;
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);
        final ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(final ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
    }

    @Override
    public ServletRequest getRequest() {
        return super.getRequest();
    }

    @Override
    public String getParameter(final String name) {
        if (!this.parametersParsed) {
            this.parseParameters();
        }
        final ArrayList<String> values = this.parameters.get(name);
        if (values == null || values.size() == 0) {
            return null;
        }
        return values.get(0);
    }

    public HashMap<String, String[]> getParameters() {
        if (!this.parametersParsed) {
            this.parseParameters();
        }
        final HashMap<String, String[]> map = new HashMap<String, String[]>(this.parameters.size() * 2);
        for (final String name : this.parameters.keySet()) {
            final ArrayList<String> values = this.parameters.get(name);
            map.put(name, values.toArray(new String[values.size()]));
        }
        return map;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Map getParameterMap() {
        return this.getParameters();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Enumeration getParameterNames() {
        return new Enumeration<String>() {
            @SuppressWarnings("unchecked")
            private final String[] arr = (String[]) (RereadableRequestWrapper.this.getParameterMap().keySet().toArray(new String[0]));
            private int index = 0;

            @Override
            public boolean hasMoreElements() {
                return this.index < this.arr.length;
            }

            @Override
            public String nextElement() {
                return this.arr[this.index++];
            }
        };
    }

    @Override
    public String[] getParameterValues(final String name) {
        if (!this.parametersParsed) {
            this.parseParameters();
        }
        final ArrayList<String> values = this.parameters.get(name);
        if (MyNullUtils.isEmpty(values)) {
            return null;
        }
        final String[] arr = values.toArray(new String[values.size()]);
        if (arr == null) {
            return null;
        }
        return arr;
    }

    private void parseParameters() {
        this.parametersParsed = true;

        if (!("application/x-www-form-urlencoded".equalsIgnoreCase(super.getContentType()))) {
            return;
        }

        int pos = 0;
        final int end = this.rawData.length;

        while (pos < end) {
            final int nameStart = pos;
            int nameEnd = -1;
            int valueStart = -1;
            int valueEnd = -1;

            boolean parsingName = true;
            boolean decodeName = false;
            boolean decodeValue = false;
            boolean parameterComplete = false;

            do {
                switch (this.rawData[pos]) {
                    case '=':
                        if (parsingName) {
                            // Name finished. Value starts from next character
                            nameEnd = pos;
                            parsingName = false;
                            valueStart = ++pos;
                        } else {
                            // Equals character in value
                            pos++;
                        }
                        break;
                    case '&':
                        if (parsingName) {
                            // Name finished. No value.
                            nameEnd = pos;
                        } else {
                            // Value finished
                            valueEnd = pos;
                        }
                        parameterComplete = true;
                        pos++;
                        break;
                    case '%':
                    case '+':
                        // Decoding required
                        if (parsingName) {
                            decodeName = true;
                        } else {
                            decodeValue = true;
                        }
                        pos++;
                        break;
                    default:
                        pos++;
                        break;
                }
            } while (!parameterComplete && pos < end);

            if (pos == end) {
                if (nameEnd == -1) {
                    nameEnd = pos;
                } else if (valueStart > -1 && valueEnd == -1) {
                    valueEnd = pos;
                }
            }

            if (nameEnd <= nameStart) {
                continue;
                // ignore invalid chunk
            }

            this.tmpName.setByteChunk(this.rawData, nameStart, nameEnd - nameStart);
            if (valueStart >= 0) {
                this.tmpValue.setByteChunk(this.rawData, valueStart, valueEnd - valueStart);
            } else {
                this.tmpValue.setByteChunk(this.rawData, 0, 0);
            }

            try {
                final String name;
                final String value;

                if (decodeName) {
                    name = new String(URLCodec.decodeUrl(Arrays.copyOfRange(this.tmpName.getBytes(), this.tmpName.getStart(), this.tmpName.getEnd())), this.encoding);
                } else {
                    name = new String(this.tmpName.getBytes(), this.tmpName.getStart(), this.tmpName.getEnd() - this.tmpName.getStart(), this.encoding);
                }

                if (valueStart >= 0) {
                    if (decodeValue) {
                        value = new String(URLCodec.decodeUrl(Arrays.copyOfRange(this.tmpValue.getBytes(), this.tmpValue.getStart(), this.tmpValue.getEnd())), this.encoding);
                    } else {
                        value = new String(this.tmpValue.getBytes(), this.tmpValue.getStart(), this.tmpValue.getEnd() - this.tmpValue.getStart(), this.encoding);
                    }
                } else {
                    value = "";
                }

                if (StringUtils.isNotBlank(name)) {
                    ArrayList<String> values = this.parameters.get(name);
                    if (values == null) {
                        values = new ArrayList<String>(1);
                        this.parameters.put(name, values);
                    }
                    if (StringUtils.isNotBlank(value)) {
                        values.add(value);
                    }
                }
            } catch (final DecoderException e) {
                // ignore invalid chunk
            }

            this.tmpName.recycle();
            this.tmpValue.recycle();
        }
    }

    private class ByteChunk {

        private byte[] buff;
        private int start = 0;
        private int end;

        public void setByteChunk(final byte[] b, final int off, final int len) {
            this.buff = b;
            this.start = off;
            this.end = this.start + len;
        }

        public byte[] getBytes() {
            return this.buff;
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        public void recycle() {
            this.buff = null;
            this.start = 0;
            this.end = 0;
        }
    }
}
