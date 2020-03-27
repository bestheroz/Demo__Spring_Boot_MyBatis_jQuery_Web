package com.github.bestheroz.sample.web.admin.valuelabel;

import com.github.bestheroz.sample.web.tablevo.samplecodedet.TableSampleCodeDetVO;
import com.github.bestheroz.sample.web.tablevo.samplecodemst.TableSampleCodeMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminValueLabelController {
    @Resource AdminValueLabelService adminValueLabelService;

    @GetMapping(value = "/sample/admin/valuelabel/adminValueLabel.view")
    public String view(final Model model) {
        return "/sample/admin/valuelabel/AdminValueLabel";
    }

    @PostMapping(value = "/sample/admin/valuelabel/getSampleCodeMstVOList.json")
    @ResponseBody
    public List<AdminValueLabelVO> getSampleCodeMstVOList(final AdminValueLabelVO vo) throws CommonException {
        return this.adminValueLabelService.getSampleCodeMstVOList(vo);
    }

    @PostMapping(value = "/sample/admin/valuelabel/insertSampleCodeMst.json")
    @ResponseBody
    public JsonObject insertSampleCodeMst(final TableSampleCodeMstVO vo) throws CommonException {
        this.adminValueLabelService.insertSampleCodeMst(vo, MySessionUtils.getLoginVO());
        return CommonException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/valuelabel/updateSampleCodeMst.json")
    @ResponseBody
    public JsonObject updateSampleCodeMst(final TableSampleCodeMstVO vo) throws CommonException {
        this.adminValueLabelService.updateSampleCodeMst(vo, MySessionUtils.getLoginVO());
        return CommonException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/valuelabel/getSampleCodeDetVOList.json")
    @ResponseBody
    public List<AdminValueLabelVO> getSampleCodeDetVOList(final AdminValueLabelVO vo) throws CommonException {
        return this.adminValueLabelService.getSampleCodeDetVOList(vo);
    }

    @PostMapping(value = "/sample/admin/valuelabel/insertSampleCodeDet.json")
    @ResponseBody
    public JsonObject insertSampleCodeDet(final TableSampleCodeDetVO vo) throws CommonException {
        this.adminValueLabelService.insertSampleCodeDet(vo, MySessionUtils.getLoginVO());
        return CommonException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/valuelabel/updateSampleCodeDet.json")
    @ResponseBody
    public JsonObject updateSampleCodeDet(final TableSampleCodeDetVO vo) throws CommonException {
        this.adminValueLabelService.updateSampleCodeDet(vo, MySessionUtils.getLoginVO());
        return CommonException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/valuelabel/deleteCOMM_CODE.json")
    @ResponseBody
    public JsonObject deleteCOMM_CODE(final TableSampleCodeMstVO vo) throws CommonException {
        this.adminValueLabelService.deleteCOMM_CODE(vo);
        return CommonException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/valuelabel/deleteSampleCodeDet.json")
    @ResponseBody
    public JsonObject deleteSampleCodeDet(final TableSampleCodeDetVO vo) throws CommonException {
        this.adminValueLabelService.deleteSampleCodeDet(vo);
        return CommonException.SUCCESS_NORMAL.getJsonObject();
    }

}
