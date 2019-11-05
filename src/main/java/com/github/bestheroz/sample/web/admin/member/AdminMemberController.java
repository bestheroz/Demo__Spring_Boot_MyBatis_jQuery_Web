package com.github.bestheroz.sample.web.admin.member;

import com.github.bestheroz.sample.web.tablevo.samplemembermst.TableSampleMemberMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminMemberController {
    @Autowired
    private AdminMemberService adminMemberService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/sample/admin/member/adminMember.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/admin/member/AdminMember";
    }

    @RequestMapping(value = "/sample/admin/member/getSampleMemberMstVOList.json", method = RequestMethod.POST)
    @ResponseBody
    public List<AdminMemberVO> getSampleMemberMstVOList(final AdminMemberVO vo) throws CommonException {
        return this.adminMemberService.getSampleMemberMstVOList(vo);
    }

    @RequestMapping(value = "/sample/admin/member/insertSampleMemberMst.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject insertSampleMemberMst(final TableSampleMemberMstVO vo) throws CommonException {
        this.adminMemberService.insertSampleMemberMst(vo, MySessionUtils.getLoginVO(this.session));
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/admin/member/updateSampleMemberMst.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject updateSampleMemberMst(final TableSampleMemberMstVO vo) throws CommonException {
        this.adminMemberService.updateSampleMemberMst(vo, MySessionUtils.getLoginVO(this.session));
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/admin/member/deleteSampleMemberMst.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject deleteSampleMemberMst(final TableSampleMemberMstVO vo) throws CommonException {
        this.adminMemberService.deleteSampleMemberMst(vo);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }
}
