package com.github.bestheroz.standard.common.exception;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
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

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {
    // 아래서 놓친 예외가 있을때 이곳으로 확인하기 위해 존재한다.
    // 놓친 예외는 이곳에서 확인하여 추가해주면 된다.
    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public JsonObject exception(final HttpServletRequest request, final Throwable e) throws Exception {
        log.warn(ExceptionUtils.getStackTrace(e));
        this.isAcceptHtml(request);
        return CommonException.FAIL_SYSTEM.getJsonObject();
    }

    @ExceptionHandler({CommonException.class})
    @ResponseBody
    public JsonObject commonResponseException(final HttpServletRequest request, final CommonException e) throws Exception {
        log.warn(e.getJsonObject().toString());
        this.isAcceptHtml(request);
        return e.getJsonObject();
    }

    @ExceptionHandler({BindException.class, MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
    @ResponseBody
    public JsonObject bindException(final HttpServletRequest request, final Throwable e) throws Exception {
        log.warn(ExceptionUtils.getStackTrace(e));
        this.isAcceptHtml(request);
        return CommonException.FAIL_INVALID_PARAMETER.getJsonObject();
    }

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class, HttpMediaTypeNotSupportedException.class, HttpRequestMethodNotSupportedException.class, HttpClientErrorException.class})
    @ResponseBody
    public JsonObject httpMediaTypeNotAcceptableException(final HttpServletRequest request, final Throwable e) throws Exception {
        log.warn(ExceptionUtils.getStackTrace(e));
        this.isAcceptHtml(request);
        return CommonException.FAIL_INVALID_REQUEST.getJsonObject();
    }

    @ExceptionHandler({MultipartException.class})
    @ResponseBody
    public JsonObject handleMultipartException(final HttpServletRequest request, final MultipartException e) throws Exception {
        log.warn(ExceptionUtils.getStackTrace(e));
        this.isAcceptHtml(request);
        final JsonObject result;
        if (ExceptionUtils.getMessage(e).contains(SizeLimitExceededException.class.getSimpleName())) {
            result = new CommonException(CommonExceptionCode.FAIL_FILE_SIZE).getJsonObject();
        } else {
            result = CommonException.FAIL_SYSTEM.getJsonObject();
        }
        return result;
    }

    private void isAcceptHtml(final HttpServletRequest request) throws Exception {
        if (StringUtils.contains(request.getHeader("accept"), "html")) {
            throw new Exception(CommonExceptionCode.FAIL_SYSTEM_ERROR.getMessage());
        }
    }
}
