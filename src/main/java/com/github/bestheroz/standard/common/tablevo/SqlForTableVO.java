package com.github.bestheroz.standard.common.tablevo;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.exception.CommonExceptionCode;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.github.bestheroz.standard.common.util.MyNullUtils;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SqlForTableVO {
    public static final String COUNT = "countTableVO";
    public static final String SELECT = "selectTableVO";
    public static final String SELECT_ONE = "selectOneTableVO";
    public static final String INSERT = "insertTableVO";
    public static final String UPDATE = "updateTableVO";
    public static final String DELETE = "deleteTableVO";
    public static final Set<String> VARCHAR_JAVA_TYPE_SET = Sets.newHashSet("String", "Char");
    public static final Set<String> SHORT_JAVA_TYPE_SET = Sets.newHashSet("Short");
    public static final Set<String> INTEGER_JAVA_TYPE_SET = Sets.newHashSet("Integer");
    public static final Set<String> BIGINT_JAVA_TYPE_SET = Sets.newHashSet("Long");
    public static final Set<String> NUMBER_JAVA_TYPE_SET = Sets.newHashSet("Short", "Integer", "Long", "Double");
    public static final Set<String> DOUBLE_JAVA_TYPE_SET = Sets.newHashSet("Double");
    public static final Set<String> TIMESTAMP_JAVA_TYPE_SET = Sets.newHashSet("DateTime", "LocalDateTime");
    public static final Set<String> BLOB_JAVA_TYPE_SET = Sets.newHashSet("Byte[]");
    public static final Set<String> BOOLEAN_JAVA_TYPE_SET = Sets.newHashSet("Boolean");
    private static final String TABLE_COLUMN_NAME_CREATED_BY = "CREATED_BY";
    private static final String TABLE_COLUMN_NAME_CREATED = "CREATED";
    private static final String TABLE_COLUMN_NAME_UPDATED = "UPDATED";
    private static final String VARIABLE_NAME_CREATED_BY = "createdBy";
    private static final String VARIABLE_NAME_CREATED = "created";
    private static final String VARIABLE_NAME_UPDATED = "updated";
    private static final String SYSDATE = "NOW()";
    private static final String ENCRYPTED_FIELD_SET = "ENCRYPTED_COLUMN_LIST";
    private static final Set<String> EXCLUDE_FIELD_SET = Sets.newHashSet("SERIAL_VERSION_U_I_D", "serialVersionUID", "E_N_C_R_Y_P_T_E_D__C_O_L_U_M_N__L_I_S_T");
    // 참고용: 각VO에 암호화 컬럼 정의 방법
    // public static transient final Set<String> ENCRYPTED_COLUMN_LIST = Sets.newHashSet("mbrMobl", "emailId").stream().collect(Collectors.toSet());
    private static final String SELECT_ENCRYPTED_STRING = "XX1.DEC_VARCHAR2_SEL ({0}, 10, ''SSN'', ''{1}'', ''{0}'') AS {0}";
    private static final String INSERT_BIND_STRING = "#'{'{0}{1}'}'";
    private static final String INSERT_BIND_ENCRYPTED_STRING = "XX1.ENC_VARCHAR2_INS (#'{'{1}{2}'}', 11, ''SSN'', ''{3}'', ''{0}'')";
    private static final String SET_BIND_STRING = "{0} = #'{'param1.{1}{2}'}'";
    private static final String SET_BIND_ENCRYPTED_STRING = "{0} = XX1.ENC_VARCHAR2_INS (#'{'param1.{1}{2}'}', 11, ''SSN'', ''{3}'', ''{0}'')";
    private static final String WHERE_BIND_STRING = "{0} = #'{'param1.{1}{2}'}'";
    private static final String WHERE_BIND_ENCRYPTED_STRING = "{0} = XX1.ENC_VARCHAR2_INS (#'{'param1.{1}{2}'}', 11, ''SSN'', ''{3}'', ''{0}'')";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public <T extends Object> String countTableVO(@NonNull final T vo, final Set<String> whereKeys) {
        final SQL sql = new SQL();
        final String tableName = this.getTableName(vo);
        sql.SELECT("COUNT(1) AS CNT").FROM(tableName);
        if (MyNullUtils.isNotEmpty(whereKeys)) {
            this.getWhereSql(vo, whereKeys, sql, tableName);
        }

        this.logger.debug(sql.toString());
        return sql.toString();
    }

    private <T extends Object> void getSelectSql(@NonNull final T vo, final SQL sql, final String tableName, final Field[] fields) {
        final Set<String> encryptedColumnList = this.getEncryptedColumnList(vo);
        for (final Field field : fields) {
            final String camelFieldName = field.getName();
            final String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelFieldName);
            if (EXCLUDE_FIELD_SET.contains(fieldName)) {
                continue;
            } else if (encryptedColumnList.contains(camelFieldName)) {
                sql.SELECT(MessageFormat.format(SELECT_ENCRYPTED_STRING, fieldName, tableName));
            } else {
                sql.SELECT(fieldName);
            }
        }
    }

    private void validWhereKey(final Set<String> whereKeys, final Map<String, Object> param) {
        if (MyNullUtils.size(whereKeys) < 1) {
            this.logger.warn(CommonExceptionCode.FAIL_INVALID_PARAMETER.toString());
            throw CommonException.EXCEPTION_FAIL_INVALID_PARAMETER;
        }

        for (final String key : whereKeys) {
            if (!param.containsKey(key) || param.get(key) == null) {
                this.logger.warn(CommonExceptionCode.FAIL_INVALID_PARAMETER.toString());
                throw CommonException.EXCEPTION_FAIL_INVALID_PARAMETER;
            }
        }
    }

    private <T extends Object> void getWhereSql(@NonNull final T vo, final Set<String> whereKeys, final SQL sql, final String tableName) {
        final Map<String, Object> param = MyMapperUtils.writeObjectAsHashMap(vo);
        try {
            this.validWhereKey(whereKeys, param);
        } catch (final Exception e) {
            // pass
        }
        final Set<String> encryptedColumnList = this.getEncryptedColumnList(vo);
        for (final Entry<String, Object> entry : param.entrySet()) {
            final String camelFieldName = entry.getKey();
            if (whereKeys.contains(camelFieldName)) {
                final String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelFieldName);
                final String columnTypeName = entry.getValue().getClass().getSimpleName();
                if (encryptedColumnList.contains(camelFieldName)) {
                    sql.WHERE(MessageFormat.format(WHERE_BIND_ENCRYPTED_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName), tableName));
                } else {
                    sql.WHERE(MessageFormat.format(WHERE_BIND_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName)));
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends Object> Set<String> getEncryptedColumnList(@NonNull final T vo) {
        try {
            return (Set<String>) vo.getClass().getField(ENCRYPTED_FIELD_SET).get(new HashSet<>());
        } catch (final IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            return new HashSet<>();
        }
    }

    @SuppressWarnings("unused")
    public <T extends Object> String selectTableVO(@NonNull final T vo, final Set<String> whereKeys, final String orderByColumns) {
        final SQL sql = new SQL();
        final String tableName = this.getTableName(vo);
        final Field[] fields = vo.getClass().getDeclaredFields();
        this.getSelectSql(vo, sql, tableName, fields);
        sql.FROM(tableName);

        if (MyNullUtils.isNotEmpty(whereKeys)) {
            this.getWhereSql(vo, whereKeys, sql, tableName);
        }
        if (StringUtils.isNotEmpty(orderByColumns)) {
            sql.ORDER_BY(orderByColumns);
        }

        this.logger.debug(sql.toString());
        return sql.toString();
    }

    private <T extends Object> String getTableName(@NonNull final T vo) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, StringUtils.substringBetween(vo.getClass().getSimpleName(), "Table", "VO"));
    }

    public <T extends Object> String selectOneTableVO(@NonNull final T vo, @NonNull final Set<String> whereKeys) {
        this.validWhereKey(whereKeys, MyMapperUtils.writeObjectAsHashMap(vo));

        final SQL sql = new SQL();
        final String tableName = this.getTableName(vo);
        final Field[] fields = vo.getClass().getDeclaredFields();
        this.getSelectSql(vo, sql, tableName, fields);
        sql.FROM(tableName);
        this.getWhereSql(vo, whereKeys, sql, tableName);
        this.logger.debug(sql.toString());
        return sql.toString();
    }

    public <T extends Object> String insertTableVO(@NonNull final T vo) {
        final Map<String, Object> param = MyMapperUtils.writeObjectAsHashMap(vo);
        final SQL sql = new SQL();
        final String tableName = this.getTableName(vo);
        sql.INSERT_INTO(tableName);
        final Set<String> encryptedColumnList = this.getEncryptedColumnList(vo);
        for (final Entry<String, Object> entry : param.entrySet()) {
            final String camelFieldName = entry.getKey();
            final String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelFieldName);
            if (StringUtils.equalsAny(camelFieldName, VARIABLE_NAME_CREATED, VARIABLE_NAME_UPDATED)) {
                sql.VALUES(fieldName, SYSDATE);
            } else {
                final String columnTypeName = entry.getValue().getClass().getSimpleName();
                if (encryptedColumnList.contains(camelFieldName)) {
                    sql.VALUES(fieldName, MessageFormat.format(INSERT_BIND_ENCRYPTED_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName), tableName));
                } else {
                    sql.VALUES(fieldName, MessageFormat.format(INSERT_BIND_STRING, camelFieldName, this.getJdbcType(columnTypeName)));
                }
            }
        }

        final Class<? extends Object> class1 = vo.getClass();
        for (final Field field : class1.getDeclaredFields()) {
            final String camelFieldName = field.getName();
            if (EXCLUDE_FIELD_SET.contains(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelFieldName))) {
                continue;
            } else if (StringUtils.equals(camelFieldName, VARIABLE_NAME_CREATED)) {
                sql.VALUES(TABLE_COLUMN_NAME_CREATED, SYSDATE);
            } else if (StringUtils.equals(camelFieldName, VARIABLE_NAME_UPDATED)) {
                sql.VALUES(TABLE_COLUMN_NAME_UPDATED, SYSDATE);
            }
        }

        this.logger.debug(sql.toString());
        return sql.toString();
    }

    public <T extends Object> String updateTableVO(@NonNull final T vo, @NotNull final Set<String> whereKeys, @Nullable final Set<String> forcedUpdateKeys) {
        final Map<String, Object> param = MyMapperUtils.writeObjectAsHashMap(vo);
        this.validWhereKey(whereKeys, param);

        final SQL sql = new SQL();
        final String tableName = this.getTableName(vo);
        sql.UPDATE(tableName);
        final Field[] fields = vo.getClass().getDeclaredFields();
        final Set<String> encryptedColumnList = this.getEncryptedColumnList(vo);
        for (final Field field : fields) {
            final String camelFieldName = field.getName();
            final String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelFieldName);
            if (EXCLUDE_FIELD_SET.contains(fieldName)) {
                continue;
            } else if (StringUtils.equalsAny(camelFieldName, VARIABLE_NAME_CREATED_BY, VARIABLE_NAME_CREATED, VARIABLE_NAME_UPDATED)) {
                continue;
            }
            final String columnTypeName = field.getType().getSimpleName();
            if (forcedUpdateKeys != null && !whereKeys.contains(camelFieldName) && (forcedUpdateKeys.contains("**") || forcedUpdateKeys.contains(camelFieldName))
                    && !StringUtils.equalsAny(camelFieldName, TABLE_COLUMN_NAME_CREATED_BY, TABLE_COLUMN_NAME_CREATED)) {
                if (encryptedColumnList.contains(camelFieldName)) {
                    sql.SET(MessageFormat.format(SET_BIND_ENCRYPTED_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName), tableName));
                } else {
                    sql.SET(MessageFormat.format(SET_BIND_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName)));
                }
            } else if (param.get(camelFieldName) != null && StringUtils.isNotEmpty(param.get(camelFieldName).toString())) {
                if (whereKeys.contains(camelFieldName)) {
                    if (encryptedColumnList.contains(camelFieldName)) {
                        sql.WHERE(MessageFormat.format(WHERE_BIND_ENCRYPTED_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName), tableName));
                    } else {
                        sql.WHERE(MessageFormat.format(WHERE_BIND_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName)));
                    }
                } else if (StringUtils.equalsAny(camelFieldName, VARIABLE_NAME_CREATED, VARIABLE_NAME_UPDATED)) {
                    sql.SET(fieldName + " = " + SYSDATE);
                } else {
                    if (encryptedColumnList.contains(camelFieldName)) {
                        sql.SET(MessageFormat.format(SET_BIND_ENCRYPTED_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName), tableName));
                    } else {
                        sql.SET(MessageFormat.format(SET_BIND_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName)));
                    }
                }
            }
        }

        final Class<? extends Object> class1 = vo.getClass();
        for (final Field field : class1.getDeclaredFields()) {
            final String camelFieldName = field.getName();
            final String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelFieldName);
            if (EXCLUDE_FIELD_SET.contains(fieldName)) {
                continue;
            } else if (StringUtils.equals(camelFieldName, VARIABLE_NAME_UPDATED)) {
                sql.SET(TABLE_COLUMN_NAME_UPDATED + " = " + SYSDATE);
            }
        }
        this.logger.debug(sql.toString());
        return sql.toString();
    }

    public <T extends Object> String deleteTableVO(@NonNull final T vo, final Set<String> whereKeys) {
        if (MyNullUtils.size(whereKeys) < 1) {
            this.logger.warn(CommonExceptionCode.FAIL_NO_DATA_SUCCESS.toString());
            throw CommonException.EXCEPTION_FAIL_NO_DATA_SUCCESS;
        }
        final Map<String, Object> param = MyMapperUtils.writeObjectAsHashMap(vo);
        for (final String key : whereKeys) {
            if (!param.containsKey(key) || param.get(key) == null) {
                this.logger.warn("{} not in {}\n{}", key, MyMapperUtils.writeObjectAsString(param), CommonExceptionCode.FAIL_INVALID_PARAMETER.toString());
                throw CommonException.EXCEPTION_FAIL_INVALID_PARAMETER;
            }
        }
        final SQL sql = new SQL();
        final String tableName = this.getTableName(vo);
        sql.DELETE_FROM(tableName);
        this.getWhereSql(vo, whereKeys, sql, tableName);

        this.logger.debug(sql.toString());
        return sql.toString();
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
            this.logger.warn("케이스 빠짐 {}", columnTypeName);
        }
        return jdbcType;
    }
}
