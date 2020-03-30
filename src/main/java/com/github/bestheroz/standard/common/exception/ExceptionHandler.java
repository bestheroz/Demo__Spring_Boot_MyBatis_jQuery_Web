package com.github.bestheroz.standard.common.exception;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    // 아래서 놓친 예외가 있을때 이곳으로 확인하기 위해 존재한다.
    // 놓친 예외는 이곳에서 확인하여 추가해주면 된다.
    @org.springframework.web.bind.annotation.ExceptionHandler({Throwable.class})
    @ResponseBody
    public JsonObject exception(final Throwable e) {
        log.warn(ExceptionUtils.getStackTrace(e));
        return BusinessException.FAIL_SYSTEM.getJsonObject();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({BusinessException.class})
    @ResponseBody
    public JsonObject response(final BusinessException e) {
        log.warn(e.getJsonObject().toString());
        return e.getJsonObject();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({BindException.class, MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
    @ResponseBody
    public JsonObject bindException(final Throwable e) {
        log.warn(ExceptionUtils.getStackTrace(e));
        return BusinessException.FAIL_INVALID_PARAMETER.getJsonObject();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(
            {HttpMediaTypeNotAcceptableException.class, HttpMediaTypeNotSupportedException.class, HttpRequestMethodNotSupportedException.class, HttpClientErrorException.class})
    @ResponseBody
    public JsonObject httpMediaTypeNotAcceptableException(final Throwable e) {
        log.warn(ExceptionUtils.getStackTrace(e));
        return BusinessException.FAIL_INVALID_REQUEST.getJsonObject();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({MultipartException.class})
    @ResponseBody
    public JsonObject handleMultipartException(final MultipartException e) {
        log.warn(ExceptionUtils.getStackTrace(e));
        final JsonObject result;
//        if (ExceptionUtils.getMessage(e).contains(FileUploadBase.SizeLimitExceededException.class.getSimpleName())) {
//            result = new Response(ExceptionCode.FAIL_FILE_SIZE).getJsonObject();
//        } else {
        result = BusinessException.FAIL_SYSTEM.getJsonObject();
//        }
        return result;
    }
}
