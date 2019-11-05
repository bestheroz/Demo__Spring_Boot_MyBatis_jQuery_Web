package com.github.bestheroz.standard.context.db.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.LocalDateTime;

import java.sql.*;

@MappedTypes(LocalDateTime.class)
@MappedJdbcTypes(value = {JdbcType.TIME, JdbcType.TIMESTAMP, JdbcType.DATE})
public class LocalDateTimeTypeHandler implements TypeHandler<LocalDateTime> {

    @Override
    public LocalDateTime getResult(final ResultSet rs, final String columnName) throws SQLException {
        final Timestamp ts = rs.getTimestamp(columnName);
        if (ts != null) {
            return new LocalDateTime(ts.getTime());
        } else {
            return null;
        }
    }

    @Override
    public LocalDateTime getResult(final ResultSet rs, final int columnIndex) throws SQLException {
        final Timestamp ts = rs.getTimestamp(columnIndex);
        if (ts != null) {
            return new LocalDateTime(ts.getTime());
        } else {
            return null;
        }
    }

    @Override
    public LocalDateTime getResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        final Timestamp ts = cs.getTimestamp(columnIndex);
        if (ts != null) {
            return new LocalDateTime(ts.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setParameter(final PreparedStatement ps, final int i, final LocalDateTime parameter, final JdbcType arg3) throws SQLException {
        if (parameter != null) {
            ps.setTimestamp(i, new Timestamp(parameter.toDate().getTime()));
        } else {
            ps.setTimestamp(i, null);
        }
    }

}
