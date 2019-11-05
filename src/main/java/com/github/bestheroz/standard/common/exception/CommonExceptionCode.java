package com.github.bestheroz.standard.common.exception;

public enum CommonExceptionCode {
    SUCCESS_NORMAL("S000", "성공"),

    FAIL_SYSTEM_ERROR("F000", "시스템에러"),

    FAIL_INVALID_REQUEST("F001", "올바르지 않은 요청"),

    FAIL_INVALID_COMMON_PARAMETER("F002", "올바르지 않은 공통 파라미터"),

    FAIL_INVALID_PARAMETER("F003", "올바르지 않은 파라미터"),

    FAIL_NOT_ALLOWED_MEMBER("F004", "허용되지 않는 사용자"),

    FAIL_FILE_NOT_FOUND("F005", "파일이 존재하지 않습니다."),

    FAIL_PATH_NOT_FOUND("F006", "경로가 존재하지 않습니다"),

    FAIL_TRANSFORM_DATA("F007", "데이터 변환 실패(Primitive -> Collection)"),

    FAIL_SELECTKEY_RETURN_NO_DATA("F008", "키 생성에 실패하였습니다."),

    FAIL_VALUE_TOO_LARGE_FOR_COLUMN("F009", "입력하신 값이 너무 큽니다."),

    FAIL_LOGIN_FAIL_CNT("F010", "로그인 실패 회수 초과"),

    FAIL_TRY_LOGIN_FIRST("F011", "처리를 위해 로그인이 필요합니다."),

    FAIL_FILE_MIMETYPE("F012", "올바르지 않은 파일 내용입니다."),

    FAIL_FILE_EXT("F013", "올바르지 않은 파일 확장자 입니다."),

    FAIL_IMAGE_MIMETYPE("F014", "올바르지 않은 이미지 형식입니다."),

    FAIL_IMAGE_EXT("F015", "올바르지 않은 이미지 확장자 입니다."),

    FAIL_EXCEL_MIMETYPE("F016", "올바르지 않은 엑셀 형식입니다."),

    FAIL_EXCEL_EXT("F017", "올바르지 않은 엑셀 확장자 입니다."),

    FAIL_WORD_MIMETYPE("F018", "올바르지 않은 워드문서 형식입니다."),

    FAIL_WORD_EXT("F019", "올바르지 않은 워드문서 확장자 입니다."),

    FAIL_PDF_MIMETYPE("F020", "올바르지 않은 PDF 문서 형식입니다."),

    FAIL_PDF_EXT("F021", "올바르지 않은 PDF 문서 확장자 입니다."),

    FAIL_FILE_SIZE("F022", "최대 2MB 이하의 파일 첨부만 가능합니다."),

    FAIL_NO_DATA_SUCCESS("F023", "처리건 없음."),

    FAIL_UNIQUE_CONSTRAINT_VIOLATED("F024", "이미 존재하는 데이터입니다."),

    FAIL_CANNOT_INSERT_NULL("F025", "필수 값을 채워주세요."),

    FAIL_INVALID_ID_PWD("F026", "입력하신 ID와 Password 정보가 없습니다."),

    FAIL_DIR_PATH_MUST_ENDS_WITH_SLASH("F027", "디렉토리 경로는 항상 '/' 으로 끝나야만 합니다."),

    FAIL_ILLEGAL_BLOCK_SIZE_AES("F028", "암/복호화 오류 발생"),

    FAIL_CONVERT_DATA("F029", "데이터 변환 실패 "),

    FAIL_INVALID_TOKEN("F030", "유효하지 않은 토큰"),

    // 이 라인 위로 새로운 메시지를 정의해주세요.
    // 개발자용 메시지는 최하위에 존재하게 해주세요

    FAIL_TO_DECRYPTION_DATA("F998", "(개발자용)암호화 값이 아닌 값을 Decryption 하고 있음!"),

    FAIL_TOO_MANY_RESULT("F996", "(개발자용)sqlSession.selectOne();에서 여러 row 리턴!"),

    FAIL_VIOLATED_CHILD_RECORD_FOUND("F997", "(개발자용)데이터를 삭제하기전 제약조건을 확인!"),

    FAIL_MAPPED_STATMENTS_COLLECTION_DOES_NOT_CONTAIN("F998", "(개발자용)SQL을 수행할 Mapper를 찾지 못함!"),

    FAIL_BAD_SQL_GRAMMER("F999", "(개발자용)SQL 문법 에러!");

    private final String code;
    private final String message;

    CommonExceptionCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.name() + "(\"" + this.code + "\", \"" + this.message + "\");";
    }
}
