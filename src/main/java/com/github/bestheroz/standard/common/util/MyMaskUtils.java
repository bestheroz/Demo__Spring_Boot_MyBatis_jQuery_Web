package com.github.bestheroz.standard.common.util;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

public class MyMaskUtils {
    protected MyMaskUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getEmail(final String email) {
        if (StringUtils.isEmpty(email)) {
            return "";
        }

        final String dirty = MyEscapeUtils.unescapeAll(email);
        final StringBuilder returnValue = new StringBuilder();

        if (StringUtils.contains(dirty, "@")) {
            final String[] split = StringUtils.split(dirty, "@", 2);

            for (int i = 0; i < split[0].length(); i++) {
                if (i % 2 == 0) {
                    returnValue.append(dirty.charAt(i));
                } else {
                    returnValue.append("*");
                }
            }
            returnValue.append("@");
            if (split.length == 2) {
                returnValue.append(split[1]);
            }
        } else {
            for (int i = 0; i < dirty.length(); i++) {
                if (i % 2 == 0) {
                    returnValue.append(dirty.charAt(i));
                } else {
                    returnValue.append("*");
                }
            }
        }
        return returnValue.toString();
    }

    public static String getId(final String id) {
        if (StringUtils.isEmpty(id)) {
            return "";
        }

        final String dirty = MyEscapeUtils.unescapeAll(id);
        final StringBuilder returnValue = new StringBuilder();

        for (int i = 0; i < dirty.length(); i++) {
            if (i % 2 == 0) {
                returnValue.append(dirty.charAt(i));
            } else {
                returnValue.append("*");
            }
        }
        return returnValue.toString();
    }

    public static String getMobileTel(final String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return "";
        }

        final StringBuilder returnValue = new StringBuilder();
        final String dirty = StringUtils.substring(RegExUtils.removeAll(MyEscapeUtils.unescapeAll(mobile), "-"), 0, 11);

        if (dirty.length() == 11) {
            returnValue.append(dirty, 0, 3).append("-****-").append(dirty.substring(7));
        } else if (dirty.length() == 10) {
            if (StringUtils.startsWith(dirty, "02")) {
                returnValue.append(dirty, 0, 2);
                returnValue.append("-****-");
                returnValue.append(dirty.substring(6));
            } else {
                returnValue.append(dirty, 0, 3);
                returnValue.append("-***-");
                returnValue.append(dirty.substring(6));
            }
        } else if (dirty.length() == 9) {
            returnValue.append(dirty, 0, 2);
            returnValue.append("-***-");
            returnValue.append(dirty.substring(5));
        } else {
            returnValue.append(dirty);
        }
        return returnValue.toString();
    }

    public static String getName(final String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }

        final String dirty = MyEscapeUtils.unescapeAll(name);
        final StringBuilder returnValue = new StringBuilder();

        for (int i = 0; i < dirty.length(); i++) {
            if (i % 2 == 0) {
                returnValue.append(dirty.charAt(i));
            } else {
                returnValue.append("*");
            }
        }

        return returnValue.toString();
    }

}
