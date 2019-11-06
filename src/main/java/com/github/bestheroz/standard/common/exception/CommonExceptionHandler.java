package com.github.bestheroz.standard.common.exception;

import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import javax.naming.SizeLimitExceededException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CommonExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);
    @Autowired
    private HttpServletRequest request;

    // 아래서 놓친 예외가 있을때 이곳으로 확인하기 위해 존재한다.
    // 놓친 예외는 이곳에서 확인하여 추가해주면 된다.
    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public JsonObject exception(final Throwable e) throws Exception {
        LOGGER.warn(ExceptionUtils.getStackTrace(e));
        this.isAcceptHtml();
        return CommonException.EXCEPTION_FAIL_SYSTEM.getJsonObject();
    }

    @ExceptionHandler({CommonException.class})
    @ResponseBody
    public JsonObject commonResponseException(final CommonException e) throws Exception {
        LOGGER.warn(e.getJsonObject().toString());
        this.isAcceptHtml();
        return e.getJsonObject();
    }

    @ExceptionHandler({BindException.class, MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
    @ResponseBody
    public JsonObject bindException(final Throwable e) throws Exception {
        LOGGER.warn(ExceptionUtils.getStackTrace(e));
        this.isAcceptHtml();
        return CommonException.EXCEPTION_FAIL_INVALID_PARAMETER.getJsonObject();
    }

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class, HttpMediaTypeNotSupportedException.class, HttpRequestMethodNotSupportedException.class, HttpClientErrorException.class})
    @ResponseBody
    public JsonObject httpMediaTypeNotAcceptableException(final Throwable e) throws Exception {
        LOGGER.warn(ExceptionUtils.getStackTrace(e));
        this.isAcceptHtml();
        return CommonException.EXCEPTION_FAIL_INVALID_REQUEST.getJsonObject();
    }

    @ExceptionHandler({MultipartException.class})
    @ResponseBody
    public JsonObject handleMultipartException(final MultipartException e) throws Exception {
        LOGGER.warn(ExceptionUtils.getStackTrace(e));
        this.isAcceptHtml();
        final JsonObject result;
        if (ExceptionUtils.getMessage(e).contains(SizeLimitExceededException.class.getSimpleName())) {
            result = new CommonException(CommonExceptionCode.FAIL_FILE_SIZE).getJsonObject();
        } else {
            result = CommonException.EXCEPTION_FAIL_SYSTEM.getJsonObject();
        }
        return result;
    }

    private void isAcceptHtml() throws Exception {
        if (StringUtils.contains(this.request.getHeader("accept"), "html")) {
            throw new Exception(CommonExceptionCode.FAIL_SYSTEM_ERROR.getMessage());
        }
    }
}
