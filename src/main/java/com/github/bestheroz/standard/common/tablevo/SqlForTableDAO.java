package com.github.bestheroz.standard.common.tablevo;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.Set;

public interface SqlForTableDAO {
    // @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
    // List<Object> getList(final Object vo, final Set<String> whereKeys, final String orderByColumns) throws Response;
    //
    // @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
    // Object getOne(final Object vo, final Set<String> whereKeys) throws Response;

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.COUNT)
    int count(final Object vo, final Set<String> whereKeys);

    @InsertProvider(type = SqlForTableVO.class, method = SqlForTableVO.INSERT)
        // @SelectKey(statement = "SELECT SEQSEQSEQSEQ.NEXTVAL FROM DUAL", keyProperty = "seq", before = true, resultType = Long.class)
    <T> void insert(final T vo);

    @UpdateProvider(type = SqlForTableVO.class, method = SqlForTableVO.UPDATE)
    <T> void update(final T vo, final Set<String> whereKeys, final Set<String> forcedUpdateKeys);

    @DeleteProvider(type = SqlForTableVO.class, method = SqlForTableVO.DELETE)
    <T> void delete(final T vo, final Set<String> whereKeys);
}
