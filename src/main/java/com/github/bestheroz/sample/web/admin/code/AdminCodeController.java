package com.github.bestheroz.sample.web.admin.code;

import com.github.bestheroz.sample.web.tablevo.samplecodedet.TableSampleCodeDetVO;
import com.github.bestheroz.sample.web.tablevo.samplecodemst.TableSampleCodeMstVO;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminCodeController {
    @Resource AdminCodeService adminCodeService;

    @GetMapping(value = "/sample/admin/code/adminCode.view")
    public String view(final Model model) {
        return "/sample/admin/code/AdminCode";
    }

    @PostMapping(value = "/sample/admin/code/getSampleCodeMstVOList.json")
    @ResponseBody
    public List<AdminCodeVO> getSampleCodeMstVOList(final AdminCodeVO vo) {
        return this.adminCodeService.getSampleCodeMstVOList(vo);
    }

    @PostMapping(value = "/sample/admin/code/insertSampleCodeMst.json")
    @ResponseBody
    public JsonObject insertSampleCodeMst(final TableSampleCodeMstVO vo) {
        this.adminCodeService.insertSampleCodeMst(vo);
        return BusinessException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/code/updateSampleCodeMst.json")
    @ResponseBody
    public JsonObject updateSampleCodeMst(final TableSampleCodeMstVO vo) {
        this.adminCodeService.updateSampleCodeMst(vo);
        return BusinessException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/code/getSampleCodeDetVOList.json")
    @ResponseBody
    public List<AdminCodeVO> getSampleCodeDetVOList(final AdminCodeVO vo) {
        return this.adminCodeService.getSampleCodeDetVOList(vo);
    }

    @PostMapping(value = "/sample/admin/code/insertSampleCodeDet.json")
    @ResponseBody
    public JsonObject insertSampleCodeDet(final TableSampleCodeDetVO vo) {
        this.adminCodeService.insertSampleCodeDet(vo);
        return BusinessException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/code/updateSampleCodeDet.json")
    @ResponseBody
    public JsonObject updateSampleCodeDet(final TableSampleCodeDetVO vo) {
        this.adminCodeService.updateSampleCodeDet(vo);
        return BusinessException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/code/deleteCOMM_CODE.json")
    @ResponseBody
    public JsonObject deleteCOMM_CODE(final TableSampleCodeMstVO vo) {
        this.adminCodeService.deleteCOMM_CODE(vo);
        return BusinessException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/code/deleteSampleCodeDet.json")
    @ResponseBody
    public JsonObject deleteSampleCodeDet(final TableSampleCodeDetVO vo) {
        this.adminCodeService.deleteSampleCodeDet(vo);
        return BusinessException.SUCCESS_NORMAL.getJsonObject();
    }

}
