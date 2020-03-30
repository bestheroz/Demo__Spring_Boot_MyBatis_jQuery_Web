package com.github.bestheroz.sample.web.tablevo.samplemembermst;

import com.github.bestheroz.standard.common.tablevo.SqlForTableDAO;
import com.github.bestheroz.standard.common.tablevo.SqlForTableVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface TableSampleMemberMstDAO extends SqlForTableDAO {

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
    List<TableSampleMemberMstVO> getList(final TableSampleMemberMstVO vo, final Set<String> whereKeys, final String orderByColumns);

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
    TableSampleMemberMstVO getVO(final TableSampleMemberMstVO vo, final Set<String> whereKeys);
}
