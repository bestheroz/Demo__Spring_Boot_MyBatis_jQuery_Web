package com.github.bestheroz.standard.common.constant;

@SuppressWarnings("ALL")
public class CommonCode {
    /**
     * <pre>
     * 이 클래스의 필드로 등록된 값들은 JSP에서 자동으로 EL 표현식으로 사용할 수 있도록 (스프링 구동시) InitWebConstantContext에서 세팅하고 있습니다.
     * 이 곳에서 상수로 정의 후 JSP에서 바로 불러 사용하시면 됩니다.
     * 예) ${PATH_JS}
     * </pre>
     */

    public static final String CONTEXT_PATH = "";
    public static final String PATH_RESOURCES = CONTEXT_PATH.concat("/resources");
    public static final String PATH_JS = PATH_RESOURCES.concat("/js");
    public static final String PATH_IMAGE = PATH_RESOURCES.concat("/images");
    public static final String PATH_CSS = PATH_RESOURCES.concat("/css");
    public static final String PATH_PLUGIN = PATH_RESOURCES.concat("/plugin");
    public static final String FILEPATH_TEXT_EDITOR = "/textEditor/";
    public static final String YES = "YES";
    public static final String NO = "NO";
    // CommonResponseException 에 정의된 필드와 같다.
    public static final String RESPONSE_CODE = "responseCode";
    public static final String RESPONSE_MESSAGE = "responseMessage";
    public static final String RESPONSE_DATA = "responseData";
    public static final String ADDITIONAL_MESSAGE = "additionalMessage";

    protected CommonCode() {
        throw new UnsupportedOperationException();
    }
}
