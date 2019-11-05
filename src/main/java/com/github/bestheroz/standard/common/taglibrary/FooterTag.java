package com.github.bestheroz.standard.common.taglibrary;

import com.github.bestheroz.standard.common.exception.CommonException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class FooterTag extends TagSupport {
    private static final long serialVersionUID = -7556199628267102752L;
    private static final Logger LOGGER = LoggerFactory.getLogger(FooterTag.class);
    private static final String FOOTER_TAG = "FOOTER_TAG";

    @Override
    public int doStartTag() {
        final ServletContext servletContext = this.pageContext.getRequest().getServletContext();

        try {
            if (servletContext.getAttribute(FOOTER_TAG) == null) {
                servletContext.setAttribute(FOOTER_TAG,
                        IOUtils.toString(new File(this.pageContext.getRequest().getServletContext().getRealPath("/WEB-INF/views/common/footer.html")).toURI(), StandardCharsets.UTF_8));
            }
            this.pageContext.getOut().print(servletContext.getAttribute(FOOTER_TAG));
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
        return SKIP_PAGE;
    }
}
