package com.github.bestheroz.standard.common.util;

import org.apache.commons.lang3.StringUtils;

public class MyPasswordUtils {

    protected MyPasswordUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * <pre>
     * ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$
     * - 최소 8자리이상
     * - 최소 하나의 숫자를 포함
     * - 최소 하나의 영문(소문/대문)자를 포함
     * - 최소 하나의 특수문자를 포함
     * - 스페이스, 탭 등을 비포함
     * </pre>
     */
    private static final String PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[~!@#$%^&*()_+=-])(?=\\S+$).{8,}$";

    /**
     * <pre>
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[~!@#$%^&*()_+=])  # a special character must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $                 # end-of-string
     * </pre>
     *
     * <pre>
     * ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~!@#$%^&*()_+=])(?=\S+$).{8,}$
     * - 최소 8자리이상
     * - 최소 하나의 숫자를 포함
     * - 최소 하나의 영문 소문자와 하나의 영문 대문자를 포함
     * - 최소 하나의 특수문자를 포함
     * - 스페이스, 탭 등을 비포함
     * </pre>
     */

    public static boolean checkPasswordSafe(final String password) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }
        return password.matches(PATTERN);
    }
}
