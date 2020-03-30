package com.github.bestheroz.sample.web.admin.member;

import com.github.bestheroz.sample.web.tablevo.samplemembermst.TableSampleMemberMstDAO;
import com.github.bestheroz.sample.web.tablevo.samplemembermst.TableSampleMemberMstVO;
import com.github.bestheroz.standard.common.util.MapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminMemberService {
    @Resource TableSampleMemberMstDAO tableMemberMstDAO;

    List<AdminMemberVO> getSampleMemberMstVOList(final AdminMemberVO vo) {
        final Set<String> whereKeys = new HashSet<>();
        if (StringUtils.isNotEmpty(vo.getMemberId())) {
            whereKeys.add("memberId");
        }
        return MapperUtils.toArrayList(this.tableMemberMstDAO.getList(MapperUtils.toObject(vo, TableSampleMemberMstVO.class), whereKeys, "UPDATED DESC"),
                AdminMemberVO.class);
    }

    void insertSampleMemberMst(final TableSampleMemberMstVO vo) {
        this.tableMemberMstDAO.insert(vo);
    }

    void updateSampleMemberMst(final TableSampleMemberMstVO vo) {
        this.tableMemberMstDAO.update(vo, Collections.singleton("memberId"), null);
    }

    void deleteSampleMemberMst(final TableSampleMemberMstVO vo) {
        this.tableMemberMstDAO.delete(vo, Collections.singleton("memberId"));
    }
}
