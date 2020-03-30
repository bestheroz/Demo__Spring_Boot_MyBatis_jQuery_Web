package com.github.bestheroz.standard.common.file;

import com.github.bestheroz.standard.common.util.FileUtils;
import com.github.bestheroz.standard.context.abstractview.AbstractDownloadView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileDownloadController {
    @RequestMapping(value = "/common/file/download/fileDownload.proc", method = RequestMethod.POST)
    public String downloadFile(@RequestParam(value = "filePath", required = false) final String filePath,
                               @RequestParam(value = "fileName", required = false) final String fileName, final Model model) {
        model.addAttribute(AbstractDownloadView.DOWNLOAD_FILE, FileUtils.getFile(filePath));
        model.addAttribute(AbstractDownloadView.DOWNLOAD_ORI_FILE_NAME, FileUtils.getFile(fileName));
        return AbstractDownloadView.VIEW_NAME;
    }
}
