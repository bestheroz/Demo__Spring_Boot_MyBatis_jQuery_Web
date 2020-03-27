package com.github.bestheroz.standard.common.file;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyFileUtils;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;

@RestController
public class FileUploadController {
    @PostMapping(value = "/common/file/upload/fileUpload")
    public JsonObject uploadFile(@RequestParam("file") final MultipartFile multipartFile, final String targetDirPath) throws CommonException {
        final JsonObject result = CommonException.SUCCESS_NORMAL.getJsonObject();
        result.addProperty("fileName", MyFileUtils.uploadFile(multipartFile, targetDirPath).getName());
        return result;
    }

    @PostMapping(value = "/common/file/upload/uploadAllFiles")
    public JsonObject uploadAllFiles(final MultipartHttpServletRequest mRequest, final String targetDirPath) throws CommonException {
        final JsonObject result = CommonException.SUCCESS_NORMAL.getJsonObject();
        final StringBuilder stringBuilder = new StringBuilder();
        for (final File file : MyFileUtils.uploadAllFiles(mRequest, targetDirPath)) {
            stringBuilder.append(file.getName()).append("\n");
        }
        result.addProperty("fileName", stringBuilder.toString());
        return result;
    }
}
