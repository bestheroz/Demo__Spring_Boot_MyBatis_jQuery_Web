package com.github.bestheroz.standard.common.util;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.exception.CommonExceptionCode;
import com.github.bestheroz.standard.common.protocol.CommonResponseVO;

public class MyResponseUtils {
    protected MyResponseUtils() {
        throw new UnsupportedOperationException();
    }

    // private static final Logger LOGGER = LoggerFactory.getLogger(MyResponseUtil.class);
    public static final CommonResponseVO SUCCESS_NORMAL = getCommonResponseVO(CommonExceptionCode.SUCCESS_NORMAL, null, null);

    public static CommonResponseVO getCommonResponseVO(final CommonExceptionCode commonExceptionCode, final Object responseData, final String additionalMessage) throws CommonException {
        final CommonResponseVO commonResponseVO = new CommonResponseVO();
        commonResponseVO.setResponseCode(commonExceptionCode.getCode());
        commonResponseVO.setResponseMessage(commonExceptionCode.getMessage());
        commonResponseVO.setResponseData(responseData);
        commonResponseVO.setAdditionalMessage(additionalMessage);
        // LOGGER.debug(MyMapperUtil.writeObjectAsJsonObject(commonResponseVO).toString());
        return commonResponseVO;
    }

    public static CommonResponseVO getCommonResponseVO(final CommonExceptionCode commonExceptionCode) throws CommonException {
        return getCommonResponseVO(commonExceptionCode, null, null);
    }

    public static CommonResponseVO getCommonResponseVO(final CommonExceptionCode commonExceptionCode, final Object responseData) throws CommonException {
        return getCommonResponseVO(commonExceptionCode, responseData, null);
    }

    public static CommonResponseVO getSuccessCommonResponseVO(final Object responseData, final String additionalMessage) throws CommonException {
        return getCommonResponseVO(CommonExceptionCode.SUCCESS_NORMAL, responseData, additionalMessage);
    }

    public static CommonResponseVO getSuccessCommonResponseVO(final Object responseData) throws CommonException {
        return getCommonResponseVO(CommonExceptionCode.SUCCESS_NORMAL, responseData, null);
    }
}
