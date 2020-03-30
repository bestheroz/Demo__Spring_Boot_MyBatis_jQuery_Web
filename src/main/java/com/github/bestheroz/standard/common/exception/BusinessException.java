package com.github.bestheroz.standard.common.exception;

import com.github.bestheroz.standard.common.response.ResponseVO;
import com.github.bestheroz.standard.common.util.MapperUtils;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.FileNotFoundException;
import java.net.BindException;
import java.util.Set;

@Slf4j
@Setter
@Getter
public class BusinessException extends RuntimeException {
    public static final BusinessException SUCCESS_NORMAL = new BusinessException(ExceptionCode.SUCCESS_NORMAL);
    public static final BusinessException FAIL_SYSTEM = new BusinessException(ExceptionCode.FAIL_SYSTEM_ERROR);
    public static final BusinessException FAIL_INVALID_REQUEST = new BusinessException(ExceptionCode.FAIL_INVALID_REQUEST);
    public static final BusinessException FAIL_INVALID_PARAMETER = new BusinessException(ExceptionCode.FAIL_INVALID_PARAMETER);
    public static final BusinessException FAIL_NOT_ALLOWED_MEMBER = new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER);
    public static final BusinessException FAIL_NO_DATA_SUCCESS = new BusinessException(ExceptionCode.FAIL_NO_DATA_SUCCESS);
    public static final BusinessException FAIL_TRY_LOGIN_FIRST = new BusinessException(ExceptionCode.FAIL_TRY_LOGIN_FIRST);
    private static final long serialVersionUID = -6837907198565524472L;
    private ResponseVO responseVO = new ResponseVO();

    public BusinessException(final Throwable throwable) {
        this.setResponseVO(throwable);
    }

    public BusinessException(final ExceptionCode exceptionCode) {
        this.setResponseVO(exceptionCode);
    }

    public BusinessException(final Throwable throwable, final Object data) {
        this.setResponseVO(throwable, data);
    }

    public BusinessException(final ExceptionCode exceptionCode, final Object data) {
        this.setResponseVO(exceptionCode, data);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public JsonObject getJsonObject() {
        if (StringUtils.isNotEmpty(this.responseVO.getCode())) {
            return MapperUtils.toJsonObject(this.responseVO);
        } else {
            return FAIL_SYSTEM.getJsonObject();
        }
    }

    private ExceptionCode getExceptionCode(final Throwable e) {
        if (e instanceof UncategorizedSQLException || e instanceof BindException) {
            return ExceptionCode.FAIL_INVALID_PARAMETER;
        } else if (e instanceof HttpMediaTypeNotAcceptableException || e instanceof HttpMediaTypeNotSupportedException || e instanceof HttpRequestMethodNotSupportedException) {
            return ExceptionCode.FAIL_INVALID_REQUEST;
        } else if (e instanceof FileNotFoundException) {
            return ExceptionCode.FAIL_FILE_NOT_FOUND;
        }
        return ExceptionCode.FAIL_SYSTEM_ERROR;
    }

    private void setResponseVO(final ExceptionCode exceptionCode) {
        this.setResponseVO(exceptionCode, null);
    }

    private void setResponseVO(final Throwable throwable) {
        this.setResponseVO(this.getExceptionCode(throwable), null);
    }

    private void setResponseVO(final ExceptionCode exceptionCode, final Object data) {
        this.responseVO.setCode(exceptionCode.getCode());
        this.responseVO.setMessage(exceptionCode.getMessage());
        if (data != null) {
            this.responseVO.setData(MapperUtils.toJsonElement(data));
        }
    }

    private void setResponseVO(final Throwable throwable, final Object data) {
        this.setResponseVO(this.getExceptionCode(throwable), data);
    }

    public boolean isEquals(final ExceptionCode exceptionCode) {
        return StringUtils.equals(this.responseVO.getCode(), exceptionCode.getCode());
    }

    public boolean isContains(final Set<ExceptionCode> sets) {
        final boolean result = false;
        for (final ExceptionCode exceptionCode : sets) {
            if (this.isEquals(exceptionCode)) {
                return true;
            }
        }
        return result;
    }

    public boolean isNoDataSuccess() {
        return this.isEquals(ExceptionCode.FAIL_NO_DATA_SUCCESS);
    }

    public boolean isSuccess() {
        return StringUtils.startsWith(this.responseVO.getCode(), "S");
    }

    public boolean isFail() {
        return StringUtils.startsWith(this.responseVO.getCode(), "F");
    }

    @Override
    public String toString() {
        return this.getJsonObject().toString();
    }
}
