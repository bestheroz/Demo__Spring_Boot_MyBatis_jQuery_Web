package com.github.bestheroz.standard.common.tablevo;

import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.util.MapperUtils;
import com.github.bestheroz.standard.common.util.NullUtils;
import com.github.bestheroz.standard.common.util.SessionUtils;
import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Slf4j
public class SqlForTableVO {
    public static final String COUNT = "count";
    public static final String SELECT = "selectList";
    public static final String SELECT_ONE = "selectOne";
    public static final String INSERT = "insert";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final Set<String> VARCHAR_JAVA_TYPE_SET = ImmutableSet.of("String", "Char");
    public static final Set<String> SHORT_JAVA_TYPE_SET = ImmutableSet.of("Short");
    public static final Set<String> INTEGER_JAVA_TYPE_SET = ImmutableSet.of("Integer");
    public static final Set<String> BIGINT_JAVA_TYPE_SET = ImmutableSet.of("Long");
    public static final Set<String> NUMBER_JAVA_TYPE_SET = ImmutableSet.of("Short", "Integer", "Long", "Double");
    public static final Set<String> DOUBLE_JAVA_TYPE_SET = ImmutableSet.of("Double");
    public static final Set<String> TIMESTAMP_JAVA_TYPE_SET = ImmutableSet.of("DateTime", "LocalDateTime");
    public static final Set<String> BLOB_JAVA_TYPE_SET = ImmutableSet.of("Byte[]");
    public static final Set<String> BOOLEAN_JAVA_TYPE_SET = ImmutableSet.of("Boolean");
    // 참고용: 각VO에 암호화 컬럼 정의 방법
    private static final Set<String> ENCRYPTED_COLUMN_LIST = ImmutableSet.of("empnm", "smsphone");
    private static final String TABLE_COLUMN_NAME_CREATED_BY = "CRT_ID";
    private static final String TABLE_COLUMN_NAME_CREATED = "CRT_DT";
    private static final String TABLE_COLUMN_NAME_UPDATED_BY = "UPD_ID";
    private static final String TABLE_COLUMN_NAME_UPDATED = "UPD_DT";
    private static final String VARIABLE_NAME_CREATED_BY = "crtId";
    private static final String VARIABLE_NAME_CREATED = "crtDt";
    private static final String VARIABLE_NAME_UPDATED_BY = "updId";
    private static final String VARIABLE_NAME_UPDATED = "updDt";
    private static final String SYSDATE = "NOW()";
    private static final Set<String> EXCLUDE_FIELD_SET = ImmutableSet.of("SERIAL_VERSION_U_I_D", "serialVersionUID", "E_N_C_R_Y_P_T_E_D__C_O_L_U_M_N__L_I_S_T");
    private static final String SELECT_ENCRYPTED_STRING = "FNC_GET_DECRYPT ({0}) AS {0}";
    private static final String INSERT_BIND_STRING = "#'{'{0}{1}'}'";
    private static final String INSERT_BIND_ENCRYPTED_STRING = "FNC_GET_ENCRYPT (#'{'{1}{2}'}')";
    private static final String SET_BIND_STRING = "{0} = #'{'param1.{1}{2}'}'";
    private static final String SET_UPDATED_BY_BIND_STRING = "{0} = ''{1}''";
    private static final String SET_BIND_ENCRYPTED_STRING = "{0} = FNC_GET_ENCRYPT (#'{'param1.{1}{2}'}')";
    private static final String WHERE_BIND_STRING = "{0} = #'{'param1.{1}{2}'}'";
    private static final String WHERE_BIND_ENCRYPTED_STRING = "{0} = FNC_GET_ENCRYPT (#'{'param1.{1}{2}'}')";

    public static String getTableName(final String javaClassName) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, StringUtils.substringBetween(javaClassName, "Table", "VO"));
    }

    public <T> String count(@NonNull final T vo, final Set<String> whereKeys) {
        final SQL sql = new SQL();
        sql.SELECT("COUNT(1) AS CNT").FROM(getTableName(vo.getClass().getSimpleName()));
        if (NullUtils.isNotEmpty(whereKeys)) {
            this.getWhereSql(vo, whereKeys, sql);
        }
        log.debug(sql.toString());
        return sql.toString();
    }

    private <T> void getSelectSql(final SQL sql, final Field[] fields) {
        for (final Field field : fields) {
            final String javaFieldName = field.getName();
            final String dbColumnName = this.getDbColumnName(javaFieldName);
            if (!EXCLUDE_FIELD_SET.contains(dbColumnName)) {
                if (ENCRYPTED_COLUMN_LIST.contains(javaFieldName)) {
                    sql.SELECT(MessageFormat.format(SELECT_ENCRYPTED_STRING, dbColumnName));
                } else {
                    sql.SELECT(dbColumnName);
                }
            }
        }
    }

    private void verifyWhereKey(final Set<String> whereKeys, final Map<String, Object> param) {
        if (NullUtils.size(whereKeys) < 1) {
            log.warn(ExceptionCode.FAIL_INVALID_PARAMETER.toString());
            throw BusinessException.FAIL_INVALID_PARAMETER;
        }

        for (final String key : whereKeys) {
            if (!param.containsKey(key) || param.get(key) == null) {
                log.warn(key.concat(": ").concat(ExceptionCode.FAIL_INVALID_PARAMETER.toString()));
                throw BusinessException.FAIL_INVALID_PARAMETER;
            }
        }
    }

    private <T> void getWhereSql(@NonNull final T vo, final Set<String> whereKeys, final SQL sql) {
        final Map<String, Object> param = MapperUtils.toHashMap(vo);
        try {
            this.verifyWhereKey(whereKeys, param);
        } catch (final Exception e) {
            // pass
        }
        for (final Entry<String, Object> entry : param.entrySet()) {
            final String javaFieldName = entry.getKey();
            if (whereKeys.contains(javaFieldName)) {
                final String dbColumnName = this.getDbColumnName(javaFieldName);
                final String columnTypeName = entry.getValue().getClass().getSimpleName();
                if (ENCRYPTED_COLUMN_LIST.contains(javaFieldName)) {
                    sql.WHERE(MessageFormat.format(WHERE_BIND_ENCRYPTED_STRING, dbColumnName, javaFieldName, this.getJdbcType(columnTypeName)));
                } else {
                    sql.WHERE(MessageFormat.format(WHERE_BIND_STRING, dbColumnName, javaFieldName, this.getJdbcType(columnTypeName)));
                }
            }
        }
    }

    @SuppressWarnings("unused")
    public <T> String selectList(@NonNull final T vo, final Set<String> whereKeys, final String orderByColumns) {
        final SQL sql = new SQL();
        final String tableName = getTableName(vo.getClass().getSimpleName());
        final Field[] fields = vo.getClass().getDeclaredFields();
        this.getSelectSql(sql, fields);
        sql.FROM(tableName);

        if (NullUtils.isNotEmpty(whereKeys)) {
            this.getWhereSql(vo, whereKeys, sql);
        }
        if (StringUtils.isNotEmpty(orderByColumns)) {
            sql.ORDER_BY(orderByColumns);
        }

        log.debug(sql.toString());
        return sql.toString();
    }

    public <T> String selectOne(@NonNull final T vo, @NonNull final Set<String> whereKeys) {
        this.verifyWhereKey(whereKeys, MapperUtils.toHashMap(vo));
        final SQL sql = new SQL();
        this.getSelectSql(sql, vo.getClass().getDeclaredFields());
        sql.FROM(getTableName(vo.getClass().getSimpleName()));
        this.getWhereSql(vo, whereKeys, sql);
        log.debug(sql.toString());
        return sql.toString();
    }

    public <T> String insert(@NonNull final T vo) {
        final Map<String, Object> param = MapperUtils.toHashMap(vo);
        final SQL sql = new SQL();
        sql.INSERT_INTO(getTableName(vo.getClass().getSimpleName()));
        for (final Entry<String, Object> entry : param.entrySet()) {
            final String javaFieldName = entry.getKey();
            final String dbColumnName = this.getDbColumnName(javaFieldName);
            if (!StringUtils.equalsAny(javaFieldName, VARIABLE_NAME_CREATED, VARIABLE_NAME_CREATED_BY, VARIABLE_NAME_UPDATED, VARIABLE_NAME_UPDATED_BY)) {
                final String columnTypeName = entry.getValue().getClass().getSimpleName();
                if (ENCRYPTED_COLUMN_LIST.contains(javaFieldName)) {
                    sql.VALUES(dbColumnName, MessageFormat.format(INSERT_BIND_ENCRYPTED_STRING, dbColumnName, javaFieldName, this.getJdbcType(columnTypeName)));
                } else {
                    sql.VALUES(dbColumnName, MessageFormat.format(INSERT_BIND_STRING, javaFieldName, this.getJdbcType(columnTypeName)));
                }
            }
        }

        final Class<?> class1 = vo.getClass();
        for (final Field field : class1.getDeclaredFields()) {
            final String javaFieldName = field.getName();
            if (!EXCLUDE_FIELD_SET.contains(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, javaFieldName))) {
                if (StringUtils.equals(javaFieldName, VARIABLE_NAME_CREATED)) {
                    sql.VALUES(TABLE_COLUMN_NAME_CREATED, SYSDATE);
                } else if (StringUtils.equals(javaFieldName, VARIABLE_NAME_UPDATED)) {
                    sql.VALUES(TABLE_COLUMN_NAME_UPDATED, SYSDATE);
                } else if (StringUtils.equals(javaFieldName, VARIABLE_NAME_CREATED_BY)) {
                    sql.VALUES(TABLE_COLUMN_NAME_CREATED_BY, "'" + SessionUtils.getMemberId() + "'");
                } else if (StringUtils.equals(javaFieldName, VARIABLE_NAME_UPDATED_BY)) {
                    sql.VALUES(TABLE_COLUMN_NAME_UPDATED_BY, "'" + SessionUtils.getMemberId() + "'");
                }
            }
        }

        log.debug(sql.toString());
        return sql.toString();
    }

    public <T> String update(@NonNull final T vo, @NotNull final Set<String> whereKeys, @Nullable final Set<String> forcedUpdateKeys) {
        final Map<String, Object> param = MapperUtils.toHashMap(vo);
        this.verifyWhereKey(whereKeys, param);

        final SQL sql = new SQL();
        sql.UPDATE(getTableName(vo.getClass().getSimpleName()));
        for (final Field field : vo.getClass().getDeclaredFields()) {
            final String javaFieldName = field.getName();
            final String dbColumnName = this.getDbColumnName(javaFieldName);
            if (EXCLUDE_FIELD_SET.contains(dbColumnName)) {
                continue;
            } else if (StringUtils.equalsAny(javaFieldName, VARIABLE_NAME_CREATED_BY, VARIABLE_NAME_CREATED, VARIABLE_NAME_UPDATED, VARIABLE_NAME_CREATED_BY, VARIABLE_NAME_UPDATED_BY)) {
                continue;
            }
            final String columnTypeName = field.getType().getSimpleName();
            if (forcedUpdateKeys != null && !whereKeys.contains(javaFieldName) && (forcedUpdateKeys.contains("**") || forcedUpdateKeys.contains(javaFieldName))
                    && !StringUtils.equalsAny(javaFieldName, TABLE_COLUMN_NAME_CREATED_BY, TABLE_COLUMN_NAME_CREATED)) {
                if (ENCRYPTED_COLUMN_LIST.contains(javaFieldName)) {
                    sql.SET(MessageFormat.format(SET_BIND_ENCRYPTED_STRING, dbColumnName, javaFieldName, this.getJdbcType(columnTypeName)));
                } else {
                    sql.SET(MessageFormat.format(SET_BIND_STRING, dbColumnName, javaFieldName, this.getJdbcType(columnTypeName)));
                }
            } else if (param.get(javaFieldName) != null) {
                if (whereKeys.contains(javaFieldName)) {
                    if (ENCRYPTED_COLUMN_LIST.contains(javaFieldName)) {
                        sql.WHERE(MessageFormat.format(WHERE_BIND_ENCRYPTED_STRING, dbColumnName, javaFieldName, this.getJdbcType(columnTypeName)));
                    } else {
                        sql.WHERE(MessageFormat.format(WHERE_BIND_STRING, dbColumnName, javaFieldName, this.getJdbcType(columnTypeName)));
                    }
                } else {
                    if (ENCRYPTED_COLUMN_LIST.contains(javaFieldName)) {
                        sql.SET(MessageFormat.format(SET_BIND_ENCRYPTED_STRING, dbColumnName, javaFieldName, this.getJdbcType(columnTypeName)));
                    } else {
                        sql.SET(MessageFormat.format(SET_BIND_STRING, dbColumnName, javaFieldName, this.getJdbcType(columnTypeName)));
                    }
                }
            }
        }

        final Class<?> class1 = vo.getClass();
        for (final Field field : class1.getDeclaredFields()) {
            final String javaFieldName = field.getName();
            if (!EXCLUDE_FIELD_SET.contains(this.getDbColumnName(javaFieldName))) {
                if (StringUtils.equals(javaFieldName, VARIABLE_NAME_UPDATED)) {
                    sql.SET(TABLE_COLUMN_NAME_UPDATED + " = " + SYSDATE);
                } else if (StringUtils.equals(javaFieldName, VARIABLE_NAME_UPDATED_BY)) {
                    sql.SET(MessageFormat.format(SET_UPDATED_BY_BIND_STRING, TABLE_COLUMN_NAME_UPDATED_BY, SessionUtils.getMemberId()));
                }
            }
        }
        if (!StringUtils.containsIgnoreCase(sql.toString(), "WHERE ")) {
            log.warn("Not Found 'WHERE'");
            throw BusinessException.FAIL_SYSTEM;
        }
        log.debug(sql.toString());
        return sql.toString();
    }

    public <T> String delete(@NonNull final T vo, final Set<String> whereKeys) {
        if (NullUtils.size(whereKeys) < 1) {
            log.warn(ExceptionCode.FAIL_NO_DATA_SUCCESS.toString());
            throw BusinessException.FAIL_NO_DATA_SUCCESS;
        }
        final Map<String, Object> param = MapperUtils.toHashMap(vo);
        for (final String key : whereKeys) {
            if (!param.containsKey(key) || param.get(key) == null) {
                log.warn("{} not in {}\n{}", key, MapperUtils.toString(param), ExceptionCode.FAIL_INVALID_PARAMETER.toString());
                throw BusinessException.FAIL_INVALID_PARAMETER;
            }
        }
        final SQL sql = new SQL();
        sql.DELETE_FROM(getTableName(vo.getClass().getSimpleName()));
        this.getWhereSql(vo, whereKeys, sql);
        if (!StringUtils.containsIgnoreCase(sql.toString(), "WHERE ")) {
            log.warn("Not Found 'WHERE'");
            throw BusinessException.FAIL_SYSTEM;
        }
        log.debug(sql.toString());
        return sql.toString();
    }

    private String getDbColumnName(final String javaFileName) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, javaFileName);
    }

    private String getJdbcType(@NonNull final String columnTypeName) {
        final String jdbcType;
        if (VARCHAR_JAVA_TYPE_SET.contains(columnTypeName)) {
            jdbcType = ", jdbcType=VARCHAR";
        } else if (SHORT_JAVA_TYPE_SET.contains(columnTypeName)) {
            jdbcType = ", jdbcType=SMALLINT";
        } else if (INTEGER_JAVA_TYPE_SET.contains(columnTypeName)) {
            jdbcType = ", jdbcType=INTEGER";
        } else if (BIGINT_JAVA_TYPE_SET.contains(columnTypeName)) {
            jdbcType = ", jdbcType=BIGINT";
        } else if (DOUBLE_JAVA_TYPE_SET.contains(columnTypeName)) {
            jdbcType = ", jdbcType=DOUBLE";
        } else if (TIMESTAMP_JAVA_TYPE_SET.contains(columnTypeName)) {
            jdbcType = ", jdbcType=TIMESTAMP";
        } else if (BOOLEAN_JAVA_TYPE_SET.contains(columnTypeName)) {
            jdbcType = ", jdbcType=BOOLEAN";
        } else if (BLOB_JAVA_TYPE_SET.contains(columnTypeName)) {
            jdbcType = ", jdbcType=BLOB";
        } else {
            jdbcType = "";
            log.warn("케이스 빠짐 {}", columnTypeName);
        }
        return jdbcType;
    }
}
