package com.github.bestheroz.sample.web.admin.member;

import com.github.bestheroz.sample.web.tablevo.samplemembermst.TableSampleMemberMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminMemberController {
    @Resource AdminMemberService adminMemberService;

    @GetMapping(value = "/sample/admin/member/adminMember.view")
    public String view() {
        return "/sample/admin/member/AdminMember";
    }

    @PostMapping(value = "/sample/admin/member/getSampleMemberMstVOList.json")
    @ResponseBody
    public List<AdminMemberVO> getSampleMemberMstVOList(final AdminMemberVO vo) throws CommonException {
        return this.adminMemberService.getSampleMemberMstVOList(vo);
    }

    @PostMapping(value = "/sample/admin/member/insertSampleMemberMst.json")
    @ResponseBody
    public JsonObject insertSampleMemberMst(final TableSampleMemberMstVO vo) throws CommonException {
        this.adminMemberService.insertSampleMemberMst(vo, MySessionUtils.getLoginVO());
        return CommonException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/member/updateSampleMemberMst.json")
    @ResponseBody
    public JsonObject updateSampleMemberMst(final TableSampleMemberMstVO vo) throws CommonException {
        this.adminMemberService.updateSampleMemberMst(vo, MySessionUtils.getLoginVO());
        return CommonException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/member/deleteSampleMemberMst.json")
    @ResponseBody
    public JsonObject deleteSampleMemberMst(final TableSampleMemberMstVO vo) throws CommonException {
        this.adminMemberService.deleteSampleMemberMst(vo);
        return CommonException.SUCCESS_NORMAL.getJsonObject();
    }
}
