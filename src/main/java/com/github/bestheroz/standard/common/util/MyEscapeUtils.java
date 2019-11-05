package com.github.bestheroz.standard.common.util;

import com.nhncorp.lucy.security.xss.XssPreventer;
import com.nhncorp.lucy.security.xss.XssSaxFilter;
import org.apache.commons.lang3.StringUtils;

public class MyEscapeUtils {
    // private static final Logger LOGGER = LoggerFactory.getLogger(MyFilterUtil.class);
    private static final XssSaxFilter XSS_SAX_FILTER = XssSaxFilter.getInstance("my-lucy-xss-sax.xml", true);
    private static final String[] REMOVE_KEYWORD = {"ftp:", "ftp!:", "javascript:", "javascript!:", "script:", "script!:", "vbscript:", "vbscript!:", "alert(", "alert!(", "expression(",
            "expression!(", "url(", "url!(", "document.cookie", "confirm(", "confirm!(", "<feff>"};

    protected MyEscapeUtils() {
        throw new UnsupportedOperationException();
    }

    public static String escapeAll(final String dirty) {
        return StringUtils.isNotEmpty(dirty) ? escapeForSecurity(escapeXss(unescapeAll(dirty))) : dirty;

    }

    private static String escapeXss(final String dirty) {
        return XssPreventer.escape(XssPreventer.unescape(XSS_SAX_FILTER.doFilter(dirty)));
    }

    // xss필터에서 추가로 필요한 문자 (, ), #, \ 때문에 재구현 하였다. 171117 by com.github.bestheroz
    private static String escapeForSecurity(final String dirty) {
        String clean = dirty;
        if (StringUtils.isNotEmpty(clean)) {
            clean = unescapeForSecurity(clean);
            // Avoid access parent path
            clean = StringUtils.removeIgnoreCase(clean, "../");

            // Avoid etc
            for (final String keyword : REMOVE_KEYWORD) {
                clean = StringUtils.removeIgnoreCase(clean, keyword);
            }

            clean = clean.replaceAll("#", "&#35;").replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;").replaceAll("&&#35;", "&#");
        }
        return clean;
    }

    public static String unescapeAll(final String clean) {
        return StringUtils.isNotEmpty(clean) ? unescapeXss(unescapeForSecurity(clean)) : clean;
    }

    private static String unescapeXss(final String clean) {
        return XssPreventer.unescape(clean);
    }

    private static String unescapeForSecurity(final String clean) {
        return clean.replaceAll("&#35;", "#").replaceAll("&#40;", "\\(").replaceAll("&#41;", "\\)").replaceAll("&#", "&&#35;");
    }
}
