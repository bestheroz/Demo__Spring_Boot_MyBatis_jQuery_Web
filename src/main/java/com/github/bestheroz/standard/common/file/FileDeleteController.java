package com.github.bestheroz.standard.common.file;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyFileUtils;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileDeleteController {
    @RequestMapping(value = "/common/file/delete/fileDelete", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject deleteFile(@RequestParam("filePath") final String filePath) throws CommonException {
        MyFileUtils.deleteFile(filePath);
        final JsonObject result = CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
        result.addProperty("fileName", filePath);
        return result;
    }

    @RequestMapping(value = "/common/file/delete/deleteAllFiles", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject deleteAllFiles(@RequestParam("filePath") final String filePath) throws CommonException {
        MyFileUtils.deleteDirectory(filePath);
        final JsonObject result = CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
        result.addProperty("fileName", filePath);
        return result;
    }
}
