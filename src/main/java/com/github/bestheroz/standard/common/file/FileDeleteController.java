package com.github.bestheroz.standard.common.file;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyFileUtils;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDeleteController {
    @PostMapping(value = "/common/file/delete/fileDelete")
    public JsonObject deleteFile(@RequestParam("filePath") final String filePath) throws CommonException {
        MyFileUtils.deleteFile(filePath);
        final JsonObject result = CommonException.SUCCESS_NORMAL.getJsonObject();
        result.addProperty("fileName", filePath);
        return result;
    }

    @PostMapping(value = "/common/file/delete/deleteAllFiles")
    public JsonObject deleteAllFiles(@RequestParam("filePath") final String filePath) throws CommonException {
        MyFileUtils.deleteDirectory(filePath);
        final JsonObject result = CommonException.SUCCESS_NORMAL.getJsonObject();
        result.addProperty("fileName", filePath);
        return result;
    }
}
