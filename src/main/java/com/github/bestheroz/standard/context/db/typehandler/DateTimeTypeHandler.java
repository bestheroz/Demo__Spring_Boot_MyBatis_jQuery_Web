package com.github.bestheroz.standard.context.db.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.sql.*;

@MappedTypes(DateTime.class)
@MappedJdbcTypes(value = {JdbcType.TIME, JdbcType.TIMESTAMP, JdbcType.DATE})
public class DateTimeTypeHandler implements TypeHandler<DateTime> {

    @Override
    public DateTime getResult(final ResultSet rs, final String columnName) throws SQLException {
        final Timestamp ts = rs.getTimestamp(columnName);
        if (ts != null) {
            return new DateTime(ts.getTime(), DateTimeZone.getDefault());
        } else {
            return null;
        }
    }

    @Override
    public DateTime getResult(final ResultSet rs, final int columnIndex) throws SQLException {
        final Timestamp ts = rs.getTimestamp(columnIndex);
        if (ts != null) {
            return new DateTime(ts.getTime(), DateTimeZone.getDefault());
        } else {
            return null;
        }
    }

    @Override
    public DateTime getResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        final Timestamp ts = cs.getTimestamp(columnIndex);
        if (ts != null) {
            return new DateTime(ts.getTime(), DateTimeZone.getDefault());
        } else {
            return null;
        }
    }

    @Override
    public void setParameter(final PreparedStatement ps, final int i, final DateTime parameter, final JdbcType arg3) throws SQLException {
        if (parameter != null) {
            ps.setTimestamp(i, new Timestamp(parameter.toDate().getTime()));
        } else {
            ps.setTimestamp(i, null);
        }
    }

}
