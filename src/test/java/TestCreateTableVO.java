package com.github.bestheroz.standard.common.tablevo;

import com.github.bestheroz.standard.context.db.checker.DbTableVOCheckerContext;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

@SpringJUnitConfig(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@Transactional
public class TestCreateTableVO {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SqlSession sqlSession;

    @Test
    public void test11() {
        try (final Statement stmt = this.sqlSession.getConnection().createStatement()) {

            final String tableName = "sample_member_mst";

            try (final ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 0")) {
                final ResultSetMetaData metaInfo = rs.getMetaData();

                // 1. VO만들기
                final StringBuilder voSb = new StringBuilder();

                for (int i = 0; i < metaInfo.getColumnCount(); i++) {
                    final String fieldType;
                    final String columnTypeName = metaInfo.getColumnTypeName(i + 1);
                    final String columnName = metaInfo.getColumnName(i + 1);
                    final String camelColumnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
                    if (DbTableVOCheckerContext.STRING_JDBC_TYPE_SET.contains(columnTypeName)) {
                        fieldType = "String";
                    } else if (StringUtils.equals(columnTypeName, "NUMBER")) {
                        if (metaInfo.getScale(i + 1) > 0) { // 소수점이 있으면
                            fieldType = "Double";
                        } else {
                            final int columnDisplaySize = metaInfo.getColumnDisplaySize(i + 1); // 실제 자리수보다 한자리 더 나옴(소수점 "." 때문일까?)
                            if (columnDisplaySize <= 5) { // 5자리 보장못함, 4자리 보장
                                fieldType = "Short"; // –32,768 ~ 32,767
                            } else if (columnDisplaySize <= 10) { // 10자리 보장못함, 9자리 보장
                                fieldType = "Integer"; // –2,147,483,648 ~ 2,147,483,647
                                // } else if (columnDisplaySize < 19) { // 19자리 보장못함, 18자리 보장, 하지만 (오라클) NUMBER형 기본형이 39다.. Long 으로 처리가자.
                                // fieldType = "Long"; // -9223372036854775808 ~ 9223372036854775807
                            } else { // 19자리 보장못함, 18자리 보장, 하지만 (오라클) NUMBER형 기본형이 39다.. Long 으로 처리가자.
                                fieldType = "Long";
                                // fieldType = "Double";
                            }
                        }
                    } else if (DbTableVOCheckerContext.NUMBER_JDBC_TYPE_SET.contains(columnTypeName)) {
                        fieldType = "Integer";
                    } else if (DbTableVOCheckerContext.DATETIME_JDBC_TYPE_SET.contains(columnTypeName)) {
                        fieldType = DbTableVOCheckerContext.DEFAULT_DATE_TYPE;
                    } else if (DbTableVOCheckerContext.BOOLEAN_JDBC_TYPE_SET.contains(columnTypeName)) {
                        fieldType = "Boolean";
                    } else if (DbTableVOCheckerContext.BYTE_JDBC_TYPE_SET.contains(columnTypeName)) {
                        fieldType = "Byte[];";
                        this.logger.debug("private Byte[] {}{}", camelColumnName, "; // XXX: spotbugs 피하기 : Arrays.copyOf(value, value.length)");
                    } else {
                        fieldType = "Unknown";
                        this.logger.warn("케이스 빠짐 {} : {}", columnName, columnTypeName);
                    }
                    voSb.append("private ").append(fieldType).append(" ").append(camelColumnName).append(";\n");
                }
                System.out.println(voSb);
            }
            System.out.println();
        } catch (final Throwable e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
        }
    }
}
