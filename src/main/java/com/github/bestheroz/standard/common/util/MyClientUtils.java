package com.github.bestheroz.standard.common.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class MyClientUtils {
    protected MyClientUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getClientIpAddr(final HttpServletRequest request) {
        final String[] headerValues = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

        String result = null;
        for (final String headerValue : headerValues) {
            final String ip = request.getHeader(headerValue);
            if (StringUtils.isNotEmpty(ip) && !StringUtils.equalsIgnoreCase(ip, "unknown")) {
                result = ip;
                break;
            }
        }

        if (StringUtils.isEmpty(result)) {
            result = request.getRemoteAddr();
        }

        return result;
    }
}
