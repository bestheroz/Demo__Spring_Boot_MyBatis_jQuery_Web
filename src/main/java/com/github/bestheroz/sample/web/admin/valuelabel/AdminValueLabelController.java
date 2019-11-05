package com.github.bestheroz.sample.web.admin.valuelabel;

import com.github.bestheroz.sample.web.tablevo.samplecodedet.TableSampleCodeDetVO;
import com.github.bestheroz.sample.web.tablevo.samplecodemst.TableSampleCodeMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminValueLabelController {
    @Autowired
    private HttpSession session;
    @Autowired
    private AdminValueLabelService adminValueLabelService;

    @RequestMapping(value = "/sample/admin/valuelabel/adminValueLabel.view", method = RequestMethod.GET)
    public String view(final Model model) {
        return "/sample/admin/valuelabel/AdminValueLabel";
    }

    @RequestMapping(value = "/sample/admin/valuelabel/getSampleCodeMstVOList.json", method = RequestMethod.POST)
    @ResponseBody
    public List<AdminValueLabelVO> getSampleCodeMstVOList(final AdminValueLabelVO vo) throws CommonException {
        return this.adminValueLabelService.getSampleCodeMstVOList(vo);
    }

    @RequestMapping(value = "/sample/admin/valuelabel/insertSampleCodeMst.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject insertSampleCodeMst(final TableSampleCodeMstVO vo) throws CommonException {
        this.adminValueLabelService.insertSampleCodeMst(vo, MySessionUtils.getLoginVO(this.session));
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/admin/valuelabel/updateSampleCodeMst.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject updateSampleCodeMst(final TableSampleCodeMstVO vo) throws CommonException {
        this.adminValueLabelService.updateSampleCodeMst(vo, MySessionUtils.getLoginVO(this.session));
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/admin/valuelabel/getSampleCodeDetVOList.json", method = RequestMethod.POST)
    @ResponseBody
    public List<AdminValueLabelVO> getSampleCodeDetVOList(final AdminValueLabelVO vo) throws CommonException {
        return this.adminValueLabelService.getSampleCodeDetVOList(vo);
    }

    @RequestMapping(value = "/sample/admin/valuelabel/insertSampleCodeDet.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject insertSampleCodeDet(final TableSampleCodeDetVO vo) throws CommonException {
        this.adminValueLabelService.insertSampleCodeDet(vo, MySessionUtils.getLoginVO(this.session));
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/admin/valuelabel/updateSampleCodeDet.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject updateSampleCodeDet(final TableSampleCodeDetVO vo) throws CommonException {
        this.adminValueLabelService.updateSampleCodeDet(vo, MySessionUtils.getLoginVO(this.session));
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/admin/valuelabel/deleteCOMM_CODE.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject deleteCOMM_CODE(final TableSampleCodeMstVO vo) throws CommonException {
        this.adminValueLabelService.deleteCOMM_CODE(vo);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/admin/valuelabel/deleteSampleCodeDet.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject deleteSampleCodeDet(final TableSampleCodeDetVO vo) throws CommonException {
        this.adminValueLabelService.deleteSampleCodeDet(vo);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

}
