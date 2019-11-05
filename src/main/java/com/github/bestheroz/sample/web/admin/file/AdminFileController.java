package com.github.bestheroz.sample.web.admin.file;

import com.github.bestheroz.sample.web.tablevo.samplefilemst.TableSampleFileMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminFileController {
    @Autowired
    private AdminFileService adminFileService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/sample/admin/file/adminFile.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/admin/file/AdminFile";
    }

    @RequestMapping(value = "/sample/admin/file/getSampleFileMstVOList.json", method = RequestMethod.POST)
    @ResponseBody
    public List<AdminFileVO> getSampleFileMstVOList(final AdminFileVO vo) throws CommonException {
        return this.adminFileService.getSampleFileMstVOList(vo);
    }

    @RequestMapping(value = "/sample/admin/file/insertSampleFileMst.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject insertSampleFileMst(final TableSampleFileMstVO vo, @RequestParam("file") final MultipartFile multipartFile) throws CommonException {
        this.adminFileService.insertSampleFileMst(vo, multipartFile, MySessionUtils.getLoginVO(this.session));
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/admin/file/updateSampleFileMst.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject updateSampleFileMst(final TableSampleFileMstVO vo, @RequestParam("file") final MultipartFile multipartFile) throws CommonException {
        this.adminFileService.updateSampleFileMst(vo, multipartFile, MySessionUtils.getLoginVO(this.session));
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/admin/file/deleteSampleFileMst.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject deleteSampleFileMst(final TableSampleFileMstVO vo) throws CommonException {
        this.adminFileService.deleteSampleFileMst(vo);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }
}
