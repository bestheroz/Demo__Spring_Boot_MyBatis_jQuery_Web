package com.github.bestheroz.standard.override;

import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.util.MapperUtils;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;

import java.util.List;
import java.util.Map;

@Slf4j
public class SqlSessionTemplateOverride extends SqlSessionTemplate {
    public SqlSessionTemplateOverride(final SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    public int delete(final String statement, final Object parameter) {
        int result = 0;
        try {
            result = super.delete(statement, this.convertJsonToMap(parameter));
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }

        if (result == 0) {
            log.warn(BusinessException.FAIL_NO_DATA_SUCCESS.toString());
            throw BusinessException.FAIL_NO_DATA_SUCCESS;
        }

        return result;
    }

    @Override
    public int delete(final String statement) {
        int result = 0;
        try {
            result = super.delete(statement);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }

        if (result == 0) {
            log.warn(BusinessException.FAIL_NO_DATA_SUCCESS.toString());
            throw BusinessException.FAIL_NO_DATA_SUCCESS;
        }

        return result;
    }

    @Override
    public int insert(final String statement) {
        int result = 0;
        try {
            result = super.insert(statement);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }

        if (result == 0) {
            log.warn(BusinessException.FAIL_NO_DATA_SUCCESS.toString());
            throw BusinessException.FAIL_NO_DATA_SUCCESS;
        }

        return result;
    }

    @Override
    public int insert(final String statement, final Object parameter) {
        int result = 0;
        try {
            result = super.insert(statement, this.convertJsonToMap(parameter));
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }

        if (result == 0) {
            log.warn(BusinessException.FAIL_NO_DATA_SUCCESS.toString());
            throw BusinessException.FAIL_NO_DATA_SUCCESS;
        }

        return result;
    }

    @Override
    public void select(final String statement, final Object parameter, final RowBounds rowBounds, final ResultHandler handler) {
        try {
            super.select(statement, this.convertJsonToMap(parameter), rowBounds, handler);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
    }

    @Override
    public void select(final String statement, final Object parameter, final ResultHandler handler) {
        try {
            super.select(statement, this.convertJsonToMap(parameter), handler);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
    }

    @Override
    public void select(final String statement, final ResultHandler handler) {
        try {
            super.select(statement, handler);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Cursor<?> selectCursor(final String statement, final Object parameter, final RowBounds rowBounds) {
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
    public Cursor<?> selectCursor(final String statement) {
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
    public Cursor<?> selectCursor(final String statement, final Object parameter) {
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
    public List<?> selectList(final String statement, final Object parameter) {
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
    public List<?> selectList(final String statement, final Object parameter, final RowBounds rowBounds) {
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
    public List<?> selectList(final String statement) {
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
    public Map<?, ?> selectMap(final String statement, final String mapKey) {
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
    public Map<?, ?> selectMap(final String statement, final Object parameter, final String mapKey, final RowBounds rowBounds) {
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
    public Map<?, ?> selectMap(final String statement, final Object parameter, final String mapKey) {
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
    public <T> T selectOne(final String statement, final Object parameter) {
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
    public <T> T selectOne(final String statement) {
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
    public int update(final String statement, final Object parameter) {
        int result = 0;
        try {
            result = super.update(statement, this.convertJsonToMap(parameter));
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }

        if (result == 0) {
            log.warn(BusinessException.FAIL_NO_DATA_SUCCESS.toString());
            throw BusinessException.FAIL_NO_DATA_SUCCESS;
        }
        return result;
    }

    @Override
    public int update(final String statement) {
        int result = 0;
        try {
            result = super.update(statement);
        } catch (final DataAccessException e) {
            this.executeThrowsDataAccessException(e);
        }

        if (result == 0) {
            log.warn(BusinessException.FAIL_NO_DATA_SUCCESS.toString());
            throw BusinessException.FAIL_NO_DATA_SUCCESS;
        }

        return result;
    }

    private <T> void writeLog(final T result) {
        try {
            log.debug(MapperUtils.toString(result));
        } catch (final Throwable e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
    }

    private Object convertJsonToMap(final Object parameter) {
        if (parameter instanceof JsonObject) {
            return MapperUtils.toHashMap(parameter);
        } else {
            return parameter;
        }
    }

    private void executeThrowsDataAccessException(final DataAccessException e) {
        log.warn("exception.class.getSimpleName() ==> {}", ExceptionUtils.getThrowableList(e).get(0).getClass().getSimpleName());
        log.warn(ExceptionUtils.getStackTrace(e));
        final Throwable throwable = ExceptionUtils.getThrowableList(e).get(0);

        // 아래 정의된 에러는 sql-error-codes.xml 에 정의된 내용입니다. sql-error-codes.xml는 구글에서 검색하세요 ^^
        final String stackTrace = ExceptionUtils.getStackTrace(throwable);
        if (throwable instanceof DuplicateKeyException) {
            throw new BusinessException(ExceptionCode.FAIL_UNIQUE_CONSTRAINT_VIOLATED);
        } else if (throwable instanceof BadSqlGrammarException) {
            throw new BusinessException(ExceptionCode.FAIL_BAD_SQL_GRAMMER);
        } else if (throwable instanceof TooManyResultsException) {
            throw new BusinessException(ExceptionCode.FAIL_TOO_MANY_RESULT);
        } else if (throwable instanceof DataIntegrityViolationException) {
            if (StringUtils.containsIgnoreCase(stackTrace, "child record found")) {
                throw new BusinessException(ExceptionCode.FAIL_VIOLATED_CHILD_RECORD_FOUND);
            } else {
                throw new BusinessException(ExceptionCode.FAIL_CANNOT_INSERT_NULL);
            }
        } else if (StringUtils.containsIgnoreCase(stackTrace, "value too large for column") || StringUtils.containsIgnoreCase(stackTrace, "value too long for column")) {
            final String substring = StringUtils.substringBetween(stackTrace.toLowerCase(), "value too", ")");
            throw new BusinessException(ExceptionCode.FAIL_VALUE_TOO_LARGE_FOR_COLUMN, StringUtils.substring(substring, StringUtils.lastIndexOf(substring, "\".\"") + 2));
        } else if (StringUtils.containsIgnoreCase(stackTrace, "SelectKey returned no data")) {
            throw new BusinessException(ExceptionCode.FAIL_SELECTKEY_RETURN_NO_DATA);
        } else if (StringUtils.containsIgnoreCase(stackTrace, "Mapped Statements collection does not contain value for")) {
            throw new BusinessException(ExceptionCode.FAIL_MAPPED_STATMENTS_COLLECTION_DOES_NOT_CONTAIN);
        } else if (StringUtils.containsIgnoreCase(stackTrace, "Fail to Decryption data")) {
            throw new BusinessException(ExceptionCode.FAIL_TO_DECRYPTION_DATA);
        } else {
            log.warn(BusinessException.FAIL_INVALID_REQUEST.toString());
            throw BusinessException.FAIL_INVALID_REQUEST;
        }
    }
}
