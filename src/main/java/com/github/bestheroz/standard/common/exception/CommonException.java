package com.github.bestheroz.standard.common.exception;

import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.github.bestheroz.standard.common.util.MyNullUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataAccessException;

import java.util.Set;

@Slf4j
@Setter
@Getter
public class CommonException extends RuntimeException {
    public static final CommonException SUCCESS_NORMAL = new CommonException(CommonExceptionCode.SUCCESS_NORMAL);
    public static final CommonException FAIL_SYSTEM = new CommonException(CommonExceptionCode.FAIL_SYSTEM_ERROR);
    public static final CommonException FAIL_INVALID_REQUEST = new CommonException(CommonExceptionCode.FAIL_INVALID_REQUEST);
    public static final CommonException FAIL_INVALID_PARAMETER = new CommonException(CommonExceptionCode.FAIL_INVALID_PARAMETER);
    public static final CommonException FAIL_NOT_ALLOWED_MEMBER = new CommonException(CommonExceptionCode.FAIL_NOT_ALLOWED_MEMBER);
    public static final CommonException FAIL_NO_DATA_SUCCESS = new CommonException(CommonExceptionCode.FAIL_NO_DATA_SUCCESS);
    public static final CommonException FAIL_TRY_LOGIN_FIRST = new CommonException(CommonExceptionCode.FAIL_TRY_LOGIN_FIRST);
    private static final long serialVersionUID = -6837907198565524472L;
    private String responseCode;
    private String responseMessage;
    private JsonElement responseData;

    public CommonException(final Exception exception) {
        log.warn("Received error message: {}", ExceptionUtils.getStackTrace(exception));
        this.setReturnValue(this.extractResultCode(exception.getClass().getSimpleName()), null);
    }

    public CommonException(final Exception exception, final Object data) {
        log.warn("Received error message: {}", ExceptionUtils.getStackTrace(exception));
        this.setReturnValue(this.extractResultCode(exception.getClass().getSimpleName()), data);
    }

    public CommonException(final DataAccessException dataAccessException) {
        log.warn("Received error message: {}", ExceptionUtils.getStackTrace(dataAccessException));
        this.setReturnValue(this.extractResultCode(dataAccessException.getClass().getSimpleName()), null);
    }

    public CommonException(final DataAccessException dataAccessException, final Object data) {
        log.warn("Received error message: {}", ExceptionUtils.getStackTrace(dataAccessException));
        this.setReturnValue(this.extractResultCode(dataAccessException.getClass().getSimpleName()), data);
    }

    public CommonException(final Throwable throwable) {
        log.warn("Received error message: {}", ExceptionUtils.getStackTrace(throwable));
        this.setReturnValue(this.extractResultCode(throwable.getClass().getSimpleName()), null);
    }

    public CommonException(final Throwable throwable, final Object data) {
        log.warn("Received error message: {}", ExceptionUtils.getStackTrace(throwable));
        this.setReturnValue(this.extractResultCode(throwable.getClass().getSimpleName()), data);
    }

    public CommonException(final CommonExceptionCode commonExceptionCode) {
        this.setReturnValue(commonExceptionCode, null);
    }

    public CommonException(final CommonExceptionCode commonExceptionCode, final Object data) {
        this.setReturnValue(commonExceptionCode, data);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public JsonObject getJsonObject() {
        if (StringUtils.isNotEmpty(this.responseCode)) {
            final JsonObject result = new JsonObject();
            result.addProperty("responseCode", this.responseCode);
            result.addProperty("responseMessage", this.responseMessage);
            if (MyNullUtils.isNotEmpty(this.responseData)) {
                result.add("responseData", MyMapperUtils.writeObjectAsJsonElement(this.responseData));
            }
            return result;
        } else {
            return FAIL_SYSTEM.getJsonObject();
        }
    }

    private CommonExceptionCode extractResultCode(final String exceptionName) {
        CommonExceptionCode commonExceptionCode = CommonExceptionCode.FAIL_SYSTEM_ERROR;
        switch (exceptionName) {
            case "UncategorizedSQLException":
            case "BindException":
                commonExceptionCode = CommonExceptionCode.FAIL_INVALID_PARAMETER;
                break;
            case "HttpMediaTypeNotAcceptableException":
            case "HttpMediaTypeNotSupportedException":
            case "HttpRequestMethodNotSupportedException":
                commonExceptionCode = CommonExceptionCode.FAIL_INVALID_REQUEST;
                break;
            case "FileNotFoundException":
                commonExceptionCode = CommonExceptionCode.FAIL_FILE_NOT_FOUND;
                break;
            default:
                break;
        }
        log.warn("{}: return value => {}", exceptionName, commonExceptionCode.getCode());
        return commonExceptionCode;
    }

    private void setReturnValue(final CommonExceptionCode commonExceptionCode, final Object data) {
        this.responseCode = commonExceptionCode.getCode();
        this.responseMessage = commonExceptionCode.getMessage();
        this.responseData = MyMapperUtils.writeObjectAsJsonElement(data);
    }

    public <T> T getResponseData(final Class<T> valueType) {
        return MyMapperUtils.writeObjectAsObject(this.responseData, valueType);
    }

    public boolean isEquals(final CommonExceptionCode commonExceptionCode) {
        return StringUtils.equals(this.getResponseCode(), commonExceptionCode.getCode());
    }

    public boolean isContains(final Set<CommonExceptionCode> sets) {
        final boolean result = false;
        for (final CommonExceptionCode commonExceptionCode : sets) {
            if (this.isEquals(commonExceptionCode)) {
                return true;
            }
        }
        return result;
    }

    public boolean isExceptionNoDataSuccess() {
        return this.isEquals(CommonExceptionCode.FAIL_NO_DATA_SUCCESS);
    }

    public boolean isSuccess() {
        return StringUtils.startsWith(this.responseCode, "S");
    }

    public boolean isFail() {
        return StringUtils.startsWith(this.responseCode, "F");
    }

    @Override
    public String toString() {
        return this.getJsonObject().toString();
    }
}
