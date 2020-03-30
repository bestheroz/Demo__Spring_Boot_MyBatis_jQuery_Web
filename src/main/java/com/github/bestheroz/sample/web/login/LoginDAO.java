package com.github.bestheroz.sample.web.login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginDAO {
    @Update("UPDATE SAMPLE_MEMBER_MST SET LOGIN_FAIL_CNT = LOGIN_FAIL_CNT + 1 WHERE MEMBER_ID = #{memberId, jdbcType=VARCHAR}")
    void updatePlusLoginFailCnt(final LoginVO vo);

    @Update("UPDATE SAMPLE_MEMBER_MST SET LOGIN_FAIL_CNT = 0 WHERE MEMBER_ID = #{memberId, jdbcType=VARCHAR}")
    void updateZeroLoginFailCnt(final LoginVO vo);
}
