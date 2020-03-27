package com.github.bestheroz.standard.common.file;

import com.github.bestheroz.standard.common.util.MyFileUtils;
import com.github.bestheroz.standard.context.abstractview.AbstractDownloadView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileDownloadController {
    @PostMapping(value = "/common/file/download/fileDownload.proc")
    public String downloadFile(@RequestParam(value = "filePath", required = false) final String filePath,
                               @RequestParam(value = "fileName", required = false) final String fileName, final Model model) {
        model.addAttribute(AbstractDownloadView.DOWNLOAD_FILE, MyFileUtils.getFile(filePath));
        model.addAttribute(AbstractDownloadView.DOWNLOAD_ORI_FILE_NAME, MyFileUtils.getFile(fileName));
        return AbstractDownloadView.VIEW_NAME;
    }
}
