package com.github.bestheroz.standard.context.abstractview;

import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.VerticalAlignment;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.file.pdf.PdfVO;
import com.github.bestheroz.standard.common.util.MyDateUtils;
import com.github.bestheroz.standard.common.util.MyNullUtils;
import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Component("AbstractPdfboxView")
@Slf4j
public abstract class AbstractPdfboxView extends AbstractView {
    /**
     * The extension to look for existing templates
     */
    public static final String EXTENSION = ".pdf";
    public static final String FILE_NAME = "fileName";
    public static final String PDF_VOS = "pdfVOs";
    public static final String LIST_DATA = "listData";
    protected PDType0Font font = null;

    /**
     * This constructor sets the appropriate content type "application/pdf". Note that IE won't take much notice of this, but there's not a lot we can do about this. Generated documents should have a
     * ".pdf" extension.
     */
    public AbstractPdfboxView() {
        this.setContentType("application/pdf");
    }

    public static void addPdfDataType(final List<PdfVO> pdfVOList, final String title, final String dbColName, final CellType cellType, final float width) {
        addPdfDataType(pdfVOList, title, dbColName, cellType, width, null);
    }

    public static void addPdfDataType(final List<PdfVO> pdfVOList, final String title, final String dbColName, final CellType cellType, final float width, final JsonElement codeObject) {
        final PdfVO pdfVO = new PdfVO();
        pdfVO.setTitle(title);
        pdfVO.setDbColName(dbColName);
        pdfVO.setCellType(cellType);
        pdfVO.setWidth(width);
        pdfVO.setCodeObject(codeObject);
        pdfVOList.add(pdfVO);
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected void renderMergedOutputModel(final Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
        try {
            // IE workaround: write into byte array first.
            final ByteArrayOutputStream baos = this.createTemporaryOutputStream();

            // Apply preferences and build metadata.
            final PDDocument document = this.newDocument();

            // Build PDF document.
            this.buildPdfDocument(model, document, request, response);
            document.save(baos);
            document.close();

            // Flush to HTTP response.
            this.writeToResponse(response, baos);
        } catch (final Throwable e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            response.setContentType("text/html;charset=utf-8");
            try (final PrintWriter pw = response.getWriter()) {
                pw.println("<script>");
                pw.println("alert('파일이 없습니다.');");
                pw.println("history.back();");
                pw.println("</script>");
            } catch (final IOException e1) {
                log.warn(ExceptionUtils.getStackTrace(e1));
                throw new CommonException(e1);
            }
        }
    }

    /**
     * Create a new document to hold the PDF contents.
     *
     * @return the newly created PDFBox Document instance
     * @see org.apache.pdfbox.pdmodel.PDDocument#PDDocument()
     */
    protected PDDocument newDocument() {
        return new PDDocument();
    }

    /**
     * Subclasses must implement this method to build an PDFBox PDF document, given the model.
     * <p>
     * Note that the passed-in HTTP response is just supposed to be used for setting cookies or other HTTP headers. The built PDF document itself will automatically get written to the response after
     * this method returns.
     *
     * @param model    the model Map
     * @param document the PDFBox Document to add elements to
     * @param request  in case we need locale etc. Shouldn't look at attributes.
     * @param response in case we need to set cookies. Shouldn't write to it.
     * @throws IOException any exception that occurred during document building
     */
    protected abstract void buildPdfDocument(Map<String, Object> model, PDDocument document, HttpServletRequest request, HttpServletResponse response) throws IOException;

    protected void writeColumnData(final List<PdfVO> pdfVOs, final Row<PDPage> row, final int j, final String data) {
        String strData = data;
        if (pdfVOs.get(j).getCellType().equals(CellType.INTEGER)) {
            this.setInteger(row, pdfVOs.get(j).getWidth(), strData);
        } else if (pdfVOs.get(j).getCellType().equals(CellType.DOUBLE)) {
            this.setDouble(row, pdfVOs.get(j).getWidth(), strData);
        } else if (pdfVOs.get(j).getCellType().equals(CellType.DATE)) {
            if (StringUtils.isNumeric(strData)) {
                strData = MyDateUtils.getString(Long.parseLong(strData), MyDateUtils.YYYY_MM_DD_HH_MM_SS);
            }
            this.setDate(row, pdfVOs.get(j).getWidth(), strData);
        } else {
            try {
                if (MyNullUtils.isNotEmpty(pdfVOs.get(j).getCodeObject()) && pdfVOs.get(j).getCodeObject().getAsJsonObject().get(strData) != null) {
                    if (pdfVOs.get(j).getCellType().equals(CellType.STRING_CENTER)) {
                        this.setStringCenter(row, pdfVOs.get(j).getWidth(), pdfVOs.get(j).getCodeObject().getAsJsonObject().get(strData).getAsString());
                    } else if (pdfVOs.get(j).getCellType().equals(CellType.STRING_RIGHT)) {
                        this.setStringRight(row, pdfVOs.get(j).getWidth(), pdfVOs.get(j).getCodeObject().getAsJsonObject().get(strData).getAsString());
                    } else {
                        this.setString(row, pdfVOs.get(j).getWidth(), pdfVOs.get(j).getCodeObject().getAsJsonObject().get(strData).getAsString());
                    }
                } else {
                    if (pdfVOs.get(j).getCellType().equals(CellType.STRING_CENTER)) {
                        this.setStringCenter(row, pdfVOs.get(j).getWidth(), strData);
                    } else if (pdfVOs.get(j).getCellType().equals(CellType.STRING_RIGHT)) {
                        this.setStringRight(row, pdfVOs.get(j).getWidth(), strData);
                    } else {
                        this.setString(row, pdfVOs.get(j).getWidth(), strData);
                    }
                }
            } catch (final Throwable e) {
                log.warn(ExceptionUtils.getStackTrace(e));
                this.setString(row, pdfVOs.get(j).getWidth(), strData);
            }
        }
    }

    private void setString(final Row<PDPage> row, final float columnWidth, final String text) {
        row.createCell(columnWidth, this.getSecureCellText(text), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE).setFont(this.font);
    }

    private void setStringCenter(final Row<PDPage> row, final float columnWidth, final String text) {
        row.createCell(columnWidth, this.getSecureCellText(text), HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE).setFont(this.font);
    }

    private void setStringRight(final Row<PDPage> row, final float columnWidth, final String text) {
        row.createCell(columnWidth, this.getSecureCellText(text), HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE).setFont(this.font);
    }

    private void setInteger(final Row<PDPage> row, final float columnWidth, final String text) {
        try {
            row.createCell(columnWidth, this.getSecureCellText(text), HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE).setFont(this.font);
        } catch (final Throwable e) {
            log.warn("Excel setInteger() error\n{}.", ExceptionUtils.getStackTrace(e));
            row.createCell(columnWidth, this.getSecureCellText(text), HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE).setFont(this.font);
        }
    }

    private void setDouble(final Row<PDPage> row, final float columnWidth, final String text) {
        try {
            row.createCell(columnWidth, this.getSecureCellText(text), HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE).setFont(this.font);
        } catch (final Throwable e) {
            log.warn("Excel setDouble() error\n{}.", ExceptionUtils.getStackTrace(e));
            row.createCell(columnWidth, this.getSecureCellText(text), HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE).setFont(this.font);
        }
    }

    private void setDate(final Row<PDPage> row, final float columnWidth, final String text) {
        try {
            this.setStringCenter(row, columnWidth, this.getSecureCellText(text));
        } catch (final Throwable e) {
            log.warn("Excel setDate() error\n{}.", ExceptionUtils.getStackTrace(e));
        }
    }

    private String getSecureCellText(final String text) {
        if (StringUtils.isEmpty(text) || StringUtils.equals(text, "null")) {
            return "";
        } else {
            return text;
        }
    }

    public enum CellType {
        STRING,

        STRING_CENTER,

        STRING_RIGHT,

        INTEGER,

        DOUBLE,

        DATE,

        DATE_YYYYMMDD,

        DATE_YYYYMMDDHHMMSS
    }
}
