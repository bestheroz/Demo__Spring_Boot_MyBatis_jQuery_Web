package com.github.bestheroz.standard.common.file;

import com.github.bestheroz.standard.common.response.ResponseVO;
import com.github.bestheroz.standard.common.util.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDeleteController {
    @PostMapping(value = "/common/file/delete/fileDelete")
    public ResponseVO deleteFile(@RequestParam("filePath") final String filePath) {
        FileUtils.deleteFile(filePath);
        return ResponseVO.SUCCESS_NORMAL;
    }

    @PostMapping(value = "/common/file/delete/deleteAllFiles")
    public ResponseVO deleteAllFiles(@RequestParam("filePath") final String filePath) {
        FileUtils.deleteDirectory(filePath);
        return ResponseVO.SUCCESS_NORMAL;
    }
}
