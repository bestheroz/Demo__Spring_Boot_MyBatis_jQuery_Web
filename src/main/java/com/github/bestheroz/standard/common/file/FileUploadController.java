package com.github.bestheroz.standard.common.file;

import com.github.bestheroz.standard.common.response.ResponseVO;
import com.github.bestheroz.standard.common.util.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
public class FileUploadController {
    @PostMapping(value = "/common/file/upload/fileUpload")
    public ResponseVO uploadFile(@RequestParam("file") final MultipartFile multipartFile, final String targetDirPath) {
        FileUtils.uploadFile(multipartFile, targetDirPath);
        return ResponseVO.SUCCESS_NORMAL;
    }

    @PostMapping(value = "/common/file/upload/uploadAllFiles")
    public ResponseVO uploadAllFiles(final MultipartHttpServletRequest mRequest, final String targetDirPath) {
        FileUtils.uploadAllFiles(mRequest, targetDirPath);
        return ResponseVO.SUCCESS_NORMAL;
    }
}
