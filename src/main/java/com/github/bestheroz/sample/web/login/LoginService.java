package com.github.bestheroz.sample.web.login;

import com.github.bestheroz.sample.web.tablevo.samplemembermst.TableSampleMemberMstDAO;
import com.github.bestheroz.sample.web.tablevo.samplemembermst.TableSampleMemberMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.exception.CommonExceptionCode;
import com.github.bestheroz.standard.common.taglibrary.MenuTag;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

@Service
@Slf4j
public class LoginService {
    @Resource LoginDAO loginDAO;
    @Resource TableSampleMemberMstDAO tableSampleMemberMstDAO;

    public void loginProcess(final LoginVO vo) throws CommonException {
        final TableSampleMemberMstVO tableSampleMemberMstVO = MyMapperUtils.writeObjectAsObject(vo, TableSampleMemberMstVO.class);
        final TableSampleMemberMstVO sampleMemberMstVO = this.tableSampleMemberMstDAO.getVO(tableSampleMemberMstVO, Collections.singleton("memberId"));

        // 로그인 관문
        // 1. 유저가 없으면
        if (sampleMemberMstVO == null) {
            log.warn(CommonException.FAIL_NOT_ALLOWED_MEMBER.getJsonObject().toString());
            throw CommonException.FAIL_NOT_ALLOWED_MEMBER;
        }

        final LoginVO loginVO = MyMapperUtils.writeObjectAsObject(sampleMemberMstVO, LoginVO.class);
        // 2. LOGIN_FAIL_CNT가 5회 이상 인가
        if (loginVO.getLoginFailCnt().intValue() >= 5) {
            log.warn(new CommonException(CommonExceptionCode.FAIL_LOGIN_FAIL_CNT).getJsonObject().toString());
            throw new CommonException(CommonExceptionCode.FAIL_LOGIN_FAIL_CNT);
        }

        // 3. 패스워드가 틀리면
        if (!StringUtils.equals(vo.getMemberPw(), loginVO.getMemberPw())) {
            this.loginDAO.updatePlusLoginFailCnt(vo);
            log.warn(CommonException.FAIL_NOT_ALLOWED_MEMBER.getJsonObject().toString());
            throw CommonException.FAIL_NOT_ALLOWED_MEMBER;
        }

        // 4. 아래는 성공
        if (loginVO.getLoginFailCnt().intValue() != 0) {
            this.loginDAO.updateZeroLoginFailCnt(vo);
        }
        MySessionUtils.setLoginVO(loginVO);
        MySessionUtils.removeAttribute(MenuTag.MENU_TAG);
        // MySessionUtil.printAttributeList(session);
    }
}
