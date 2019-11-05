package com.github.bestheroz.sample.web.login;

import com.github.bestheroz.standard.common.exception.CommonException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LoginDAO {
    @Update("UPDATE SAMPLE_MEMBER_MST SET LOGIN_FAIL_CNT = LOGIN_FAIL_CNT + 1 WHERE MEMBER_ID = #{memberId, jdbcType=VARCHAR}")
    void updatePlusLoginFailCnt(final LoginVO vo) throws CommonException;

    @Update("UPDATE SAMPLE_MEMBER_MST SET LOGIN_FAIL_CNT = 0 WHERE MEMBER_ID = #{memberId, jdbcType=VARCHAR}")
    void updateZeroLoginFailCnt(final LoginVO vo) throws CommonException;
}