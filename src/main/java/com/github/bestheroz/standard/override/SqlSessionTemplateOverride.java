package com.github.bestheroz.standard.override;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.exception.CommonExceptionCode;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;

import java.util.List;
import java.util.Map;

public class SqlSessionTemplateOverride extends SqlSessionTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlSessionTemplateOverride.class);

    public SqlSessionTemplateOverride(final SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    public int delete(final String statement, final Object parameter) throws CommonException {
        int result = 0;
        try {
            result = super.delete(statement, this.convertJsonToMap(parameter));
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }

        if (result == 0) {
            LOGGER.warn(CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS.getJsonObject().toString());
            throw CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS;
        }

        return result;
    }

    @Override
    public int delete(final String statement) throws CommonException {
        int result = 0;
        try {
            result = super.delete(statement);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }

        if (result == 0) {
            LOGGER.warn(CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS.getJsonObject().toString());
            throw CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS;
        }

        return result;
    }

    @Override
    public int insert(final String statement) throws CommonException {
        int result = 0;
        try {
            result = super.insert(statement);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }

        if (result == 0) {
            LOGGER.warn(CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS.getJsonObject().toString());
            throw CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS;
        }

        return result;
    }

    @Override
    public int insert(final String statement, final Object parameter) throws CommonException {
        int result = 0;
        try {
            result = super.insert(statement, this.convertJsonToMap(parameter));
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }

        if (result == 0) {
            LOGGER.warn(CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS.getJsonObject().toString());
            throw CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS;
        }

        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void select(final String statement, final Object parameter, final RowBounds rowBounds, final ResultHandler handler) throws CommonException {
        try {
            super.select(statement, this.convertJsonToMap(parameter), rowBounds, handler);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void select(final String statement, final Object parameter, final ResultHandler handler) throws CommonException {
        try {
            super.select(statement, this.convertJsonToMap(parameter), handler);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void select(final String statement, final ResultHandler handler) throws CommonException {
        try {
            super.select(statement, handler);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Cursor<?> selectCursor(final String statement, final Object parameter, final RowBounds rowBounds) throws CommonException {
        Cursor<?> result = null;
        try {
            result = super.selectCursor(statement, this.convertJsonToMap(parameter), rowBounds);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Cursor<?> selectCursor(final String statement) throws CommonException {
        Cursor<?> result = null;
        try {
            result = super.selectCursor(statement);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Cursor<?> selectCursor(final String statement, final Object parameter) throws CommonException {
        Cursor<?> result = null;
        try {
            result = super.selectCursor(statement, this.convertJsonToMap(parameter));
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<?> selectList(final String statement, final Object parameter) throws CommonException {
        List<?> result = null;
        try {
            result = super.selectList(statement, this.convertJsonToMap(parameter));
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
        this.writeLog(result);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<?> selectList(final String statement, final Object parameter, final RowBounds rowBounds) throws CommonException {
        List<?> result = null;
        try {
            result = super.selectList(statement, this.convertJsonToMap(parameter), rowBounds);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
        this.writeLog(result);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<?> selectList(final String statement) throws CommonException {
        List<?> result = null;
        try {
            result = super.selectList(statement);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
        this.writeLog(result);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<?, ?> selectMap(final String statement, final String mapKey) throws CommonException {
        Map<?, ?> result = null;
        try {
            result = super.selectMap(statement, mapKey);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
        this.writeLog(result);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<?, ?> selectMap(final String statement, final Object parameter, final String mapKey, final RowBounds rowBounds) throws CommonException {
        Map<?, ?> result = null;
        try {
            result = super.selectMap(statement, this.convertJsonToMap(parameter), mapKey, rowBounds);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
        this.writeLog(result);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<?, ?> selectMap(final String statement, final Object parameter, final String mapKey) throws CommonException {
        Map<?, ?> result = null;
        try {
            result = super.selectMap(statement, this.convertJsonToMap(parameter), mapKey);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
        this.writeLog(result);
        return result;
    }

    @Override
    public <T> T selectOne(final String statement, final Object parameter) throws CommonException {
        T result = null;
        try {
            result = super.selectOne(statement, this.convertJsonToMap(parameter));
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
        this.writeLog(result);
        return result;
    }

    @Override
    public <T> T selectOne(final String statement) throws CommonException {
        T result = null;
        try {
            result = super.selectOne(statement);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
        this.writeLog(result);
        return result;
    }

    @Override
    public int update(final String statement, final Object parameter) throws CommonException {
        int result = 0;
        try {
            result = super.update(statement, this.convertJsonToMap(parameter));
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }

        if (result == 0) {
            LOGGER.warn(CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS.getJsonObject().toString());
            throw CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS;
        }
        return result;
    }

    @Override
    public int update(final String statement) throws CommonException {
        int result = 0;
        try {
            result = super.update(statement);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }

        if (result == 0) {
            LOGGER.warn(CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS.getJsonObject().toString());
            throw CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS;
        }

        return result;
    }

    private <T> void writeLog(final T result) {
        try {
            LOGGER.debug(MyMapperUtils.writeObjectAsString(result));
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
        }
    }

    private Object convertJsonToMap(final Object parameter) {
        if (parameter instanceof JsonObject) {
            return MyMapperUtils.writeObjectAsHashMap(parameter);
        } else {
            return parameter;
        }
    }

    private void executeThrowsDataAccessException(final DataAccessException e) throws CommonException {
        LOGGER.warn("exception.class.getSimpleName() ==> {}", ExceptionUtils.getThrowableList(e).get(0).getClass().getSimpleName());
        LOGGER.warn(ExceptionUtils.getStackTrace(e));
        final Throwable throwable = ExceptionUtils.getThrowableList(e).get(0);

        // 아래 정의된 에러는 sql-error-codes.xml 에 정의된 내용입니다. sql-error-codes.xml는 구글에서 검색하세요 ^^
        final String stackTrace = ExceptionUtils.getStackTrace(throwable);
        if (throwable instanceof DuplicateKeyException) {
            throw new CommonException(CommonExceptionCode.FAIL_UNIQUE_CONSTRAINT_VIOLATED);
        } else if (throwable instanceof BadSqlGrammarException) {
            throw new CommonException(CommonExceptionCode.FAIL_BAD_SQL_GRAMMER);
        } else if (throwable instanceof TooManyResultsException) {
            throw new CommonException(CommonExceptionCode.FAIL_TOO_MANY_RESULT);
        } else if (throwable instanceof DataIntegrityViolationException) {
            if (StringUtils.containsIgnoreCase(stackTrace, "child record found")) {
                throw new CommonException(CommonExceptionCode.FAIL_VIOLATED_CHILD_RECORD_FOUND);
            } else {
                throw new CommonException(CommonExceptionCode.FAIL_CANNOT_INSERT_NULL);
            }
        } else if (StringUtils.containsIgnoreCase(stackTrace, "value too large for column") || StringUtils.containsIgnoreCase(stackTrace, "value too long for column")) {
            final String substring = StringUtils.substringBetween(stackTrace.toLowerCase(), "value too", ")");
            throw new CommonException(CommonExceptionCode.FAIL_VALUE_TOO_LARGE_FOR_COLUMN, StringUtils.substring(substring, StringUtils.lastIndexOf(substring, "\".\"") + 2));
        } else if (StringUtils.containsIgnoreCase(stackTrace, "SelectKey returned no data")) {
            throw new CommonException(CommonExceptionCode.FAIL_SELECTKEY_RETURN_NO_DATA);
        } else if (StringUtils.containsIgnoreCase(stackTrace, "Mapped Statements collection does not contain value for")) {
            throw new CommonException(CommonExceptionCode.FAIL_MAPPED_STATMENTS_COLLECTION_DOES_NOT_CONTAIN);
        } else if (StringUtils.containsIgnoreCase(stackTrace, "Fail to Decryption data")) {
            throw new CommonException(CommonExceptionCode.FAIL_TO_DECRYPTION_DATA);
        } else {
            LOGGER.warn(CommonException.EXCEPTION_ERROR_INVALID_REQUEST.getJsonObject().toString());
            throw CommonException.EXCEPTION_ERROR_INVALID_REQUEST;
        }
    }
}
