package com.github.bestheroz.standard.context.logging;

import com.github.bestheroz.standard.common.util.MyMapperUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CloseShieldInputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Configuration
public class RequestLoggingFilter extends CommonsRequestLoggingFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private static final String REQUEST_PARAMETERS = "<{}>{}, parameters={}";

    @Override
    protected void beforeRequest(final HttpServletRequest request, final String message) {
        final String method = request.getMethod();
        if (StringUtils.equalsIgnoreCase(method, "GET")) {
            LOGGER.info(REQUEST_PARAMETERS, method, new UrlPathHelper().getPathWithinApplication(request), MyMapperUtils.writeObjectAsJsonObject(request.getParameterMap()));
        } else {
            try {
                LOGGER.info(REQUEST_PARAMETERS, method, new UrlPathHelper().getPathWithinApplication(request), StringUtils.remove(IOUtils.toString(
                        IOUtils.toByteArray(new CloseShieldInputStream(request.getInputStream())), StandardCharsets.UTF_8.name()), "\n"));
            } catch (final Throwable e) {
                LOGGER.warn(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    @Override
    protected void afterRequest(final HttpServletRequest request, final String message) {
        super.afterRequest(request, message);
    }
}
