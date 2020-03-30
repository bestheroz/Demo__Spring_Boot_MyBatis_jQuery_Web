package com.github.bestheroz.standard.context.abstractview;

import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.util.FileUtils;
import com.github.bestheroz.standard.common.util.NullUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@Slf4j
public class AbstractDownloadView extends AbstractView {
    public static final String DOWNLOAD_FILE = "downloadFile";
    public static final String VIEW_NAME = "downloadView";
    public static final String DOWNLOAD_ORI_FILE_NAME = "oriDownloadFileName";

    @Override
    protected void renderMergedOutputModel(final Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
        final File file = (File) model.get(DOWNLOAD_FILE);
        if (!NullUtils.exists(file)) {
            log.warn("{} {}", ExceptionCode.FAIL_FILE_NOT_FOUND.toString(), file.getAbsolutePath());
            response.setContentType("text/html;charset=utf-8");
            try (final PrintWriter pw = response.getWriter()) {
                pw.println("<script>");
                pw.println("alert('파일이 없습니다.');");
                pw.println("history.back();");
                pw.println("</script>");
                return;
            } catch (final IOException e) {
                log.warn(ExceptionUtils.getStackTrace(e));
                throw new BusinessException(e);
            }
        }
        try (final OutputStream out = response.getOutputStream(); final FileInputStream fis = new FileInputStream(file)) {
            log.info("file.getPath() : {}", file.getPath());
            log.info("file.getName() : {}", file.getName());

            if (FileUtils.isFileType(file, FileUtils.FileType.PDF)) {
                response.setContentType("application/pdf");
            } else {
                response.setContentType("application/download;charset=utf-8");

                response.setContentLength((int) file.length());

                String oriFileName = (String) model.get(AbstractDownloadView.DOWNLOAD_ORI_FILE_NAME);
                if (StringUtils.isEmpty(oriFileName)) {
                    oriFileName = FileUtils.getEncodedFileName(request, file.getName());
                }
                response.setHeader("Content-Disposition", "attachment; filename=\"" + FileUtils.getEncodedFileName(request, oriFileName) + "\";");
                response.setHeader("Content-Transfer-Encoding", "binary");
            }

            FileCopyUtils.copy(fis, out);
            out.flush();
        } catch (final IOException e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new BusinessException(e);
        }
    }
}
