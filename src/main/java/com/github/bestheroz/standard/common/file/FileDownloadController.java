package com.github.bestheroz.standard.common.file;

import com.github.bestheroz.sample.web.common.util.MyBlobFileUtils;
import com.github.bestheroz.standard.common.util.MyFileUtils;
import com.github.bestheroz.standard.context.abstractview.AbstractDownloadView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileDownloadController {
    @RequestMapping(value = "/common/file/download/fileDownload.proc", method = RequestMethod.POST)
    public String downloadFile(@RequestParam(value = "filePath", required = false) final String filePath, @RequestParam(value = "fileSeq", required = false) final Integer fileSeq,
                               @RequestParam(value = "fileName", required = false) final String fileName, final Model model) {
        if (fileSeq != null) {
            model.addAttribute(AbstractDownloadView.DOWNLOAD_FILE, MyBlobFileUtils.getFileFromSampleFileMstObject(fileSeq, fileName));
        } else if (StringUtils.isNotEmpty(filePath)) {
            model.addAttribute(AbstractDownloadView.DOWNLOAD_FILE, MyFileUtils.getFile(filePath));
        }
        return AbstractDownloadView.VIEW_NAME;
    }
}
