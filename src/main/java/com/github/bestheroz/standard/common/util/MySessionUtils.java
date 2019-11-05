package com.github.bestheroz.standard.common.util;

import com.github.bestheroz.sample.web.login.LoginVO;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class MySessionUtils {
    public static final String SESSION_VALUE_OF_LOGIN_VO = "loginVO";
    private static final Logger LOGGER = LoggerFactory.getLogger(MySessionUtils.class);

    protected MySessionUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean isLogined(final HttpSession session) {
        return session.getAttribute(SESSION_VALUE_OF_LOGIN_VO) != null;
    }

    public static boolean isNotLogined(final HttpSession session) {
        return !isLogined(session);
    }

    public static LoginVO getLoginVO(final HttpSession session) {
        try {
            return (LoginVO) session.getAttribute(SESSION_VALUE_OF_LOGIN_VO);
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static void setLoginVO(final HttpSession session, final LoginVO loginVO) {
        session.setAttribute(SESSION_VALUE_OF_LOGIN_VO, loginVO);
        session.setAttribute("memberId", loginVO.getMemberId());
        session.setAttribute("memberName", loginVO.getMemberName());
        session.setAttribute("memberType", loginVO.getMemberType());
    }

    public static void logout(final HttpSession session) {
        final JsonObject result = new JsonObject();
        try {
            for (final Enumeration<String> attributeNames = session.getAttributeNames();
                 attributeNames.hasMoreElements(); ) {
                final String nextElement = attributeNames.nextElement();
                result.addProperty(nextElement, MyMapperUtils.writeObjectAsString(session.getAttribute(nextElement)));
                session.removeAttribute(nextElement);
            }
            LOGGER.debug("(because logout)remove all session's attirubet : {}", result.toString());
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
        }
    }

    public static boolean hasAttribute(final HttpSession httpSession, final String name) {
        try {
            return httpSession.getAttribute(name) != null;
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    public static String getAttribute(final HttpSession session, final String name) {
        try {
            return (String) session.getAttribute(name);
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static void setAttribute(final HttpSession session, final String name, final Object value) {
        session.setAttribute(name, value);
    }

    public static Integer getAttributeInteger(final HttpSession session, final String name) {
        try {
            return (Integer) session.getAttribute(name);
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static Object getAttributeObject(final HttpSession session, final String name) {
        try {
            return session.getAttribute(name);
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getAttributeObject(final HttpSession session, final String name, final Class<T> returnType) {
        try {
            return (T) session.getAttribute(name);
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static void removeAttribute(final HttpSession session, final String name) {
        session.removeAttribute(name);
    }

    public static void printAttributeList(final HttpSession session) {
        final JsonObject result = new JsonObject();
        try {
            for (final Enumeration<String> attributeNames = session.getAttributeNames();
                 attributeNames.hasMoreElements(); ) {
                final String nextElement = attributeNames.nextElement();
                result.addProperty(nextElement, MyMapperUtils.writeObjectAsString(session.getAttribute(nextElement)));
            }
            LOGGER.debug(result.toString());
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
        }
    }

    public static JsonObject getAttributeJson(final HttpSession session) {
        final JsonObject result = new JsonObject();
        try {
            for (final Enumeration<String> attributeNames = session.getAttributeNames();
                 attributeNames.hasMoreElements(); ) {
                final String nextElement = attributeNames.nextElement();
                result.addProperty(nextElement, MyMapperUtils.writeObjectAsString(session.getAttribute(nextElement)));
            }
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }
}
