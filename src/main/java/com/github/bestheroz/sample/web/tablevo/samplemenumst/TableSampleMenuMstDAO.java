package com.github.bestheroz.sample.web.tablevo.samplemenumst;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.tablevo.SqlForTableDAO;
import com.github.bestheroz.standard.common.tablevo.SqlForTableVO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface TableSampleMenuMstDAO extends SqlForTableDAO {

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
    List<TableSampleMenuMstVO> getList(final TableSampleMenuMstVO vo, final Set<String> whereKeys, final String orderByColumns) throws CommonException;

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
    TableSampleMenuMstVO getVO(final TableSampleMenuMstVO vo, final Set<String> whereKeys) throws CommonException;

    @Override
    @InsertProvider(type = SqlForTableVO.class, method = SqlForTableVO.INSERT)
    <T> void insert(final T vo) throws CommonException;

}
