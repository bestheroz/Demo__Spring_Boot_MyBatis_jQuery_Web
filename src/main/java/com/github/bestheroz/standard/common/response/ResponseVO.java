package com.github.bestheroz.standard.common.response;

import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.util.MapperUtils;
import com.google.gson.JsonElement;
import lombok.Data;

@Data
public class ResponseVO {
    public static final ResponseVO SUCCESS_NORMAL = new ResponseVO(ExceptionCode.SUCCESS_NORMAL);
    private String code;
    private String message;
    private JsonElement data;
    private Integer dataTotalLength;

    public ResponseVO() {
    }

    public ResponseVO(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }

    public static ResponseVO getSuccessResponseVO(final Object data) {
        return getSuccessResponseVO(data, null);
    }

    public static ResponseVO getSuccessResponseVO(final Object data, final Integer dataTotalLength) {
        final ResponseVO responseVO = new ResponseVO(ExceptionCode.SUCCESS_NORMAL);
        responseVO.setData(MapperUtils.toJsonElement(data));
        responseVO.setDataTotalLength(dataTotalLength);
        return responseVO;
    }
}
