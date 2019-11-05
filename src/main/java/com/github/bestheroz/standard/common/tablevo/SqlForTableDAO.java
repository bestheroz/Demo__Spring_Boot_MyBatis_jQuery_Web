package com.github.bestheroz.standard.common.tablevo;

import com.github.bestheroz.standard.common.exception.CommonException;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.Set;

@SuppressWarnings("ALL")
public interface SqlForTableDAO {
    // @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
    // List<Object> getList(final Object vo, final Set<String> whereKeys, final String orderByColumns) throws CommonException;
    //
    // @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
    // Object getOne(final Object vo, final Set<String> whereKeys) throws CommonException;

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.COUNT)
    int count(final Object vo, final Set<String> whereKeys) throws CommonException;

    @SuppressWarnings("unused")
    @InsertProvider(type = SqlForTableVO.class, method = SqlForTableVO.INSERT)
        // @SelectKey(statement = "SELECT SEQSEQSEQSEQ.NEXTVAL FROM DUAL", keyProperty = "seq", before = true, resultType = Long.class)
    <T extends Object> void insert(final T vo) throws CommonException;

    @UpdateProvider(type = SqlForTableVO.class, method = SqlForTableVO.UPDATE)
    <T extends Object> void update(final T vo, final Set<String> whereKeys, final Set<String> forcedUpdateKeys) throws CommonException;

    @DeleteProvider(type = SqlForTableVO.class, method = SqlForTableVO.DELETE)
    <T extends Object> void delete(final T vo, final Set<String> whereKeys) throws CommonException;
}