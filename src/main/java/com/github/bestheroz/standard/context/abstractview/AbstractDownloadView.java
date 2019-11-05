package com.github.bestheroz.standard.context.abstractview;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.exception.CommonExceptionCode;
import com.github.bestheroz.standard.common.util.MyFileUtils;
import com.github.bestheroz.standard.common.util.MyFileUtils.FileType;
import com.github.bestheroz.standard.common.util.MyNullUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

public class AbstractDownloadView extends AbstractView {
    public static final String DOWNLOAD_FILE = "downloadFile";
    public static final String VIEW_NAME = "downloadView";
    private static final String DOWNLOAD_ORI_FILE_NAME = "oriDownloadFileName";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void renderMergedOutputModel(final Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) throws CommonException {
        final File file = (File) model.get(DOWNLOAD_FILE);
        if (!MyNullUtils.exists(file)) {
            this.logger.warn("{} {}", CommonExceptionCode.FAIL_FILE_NOT_FOUND.getMessage(), file.getAbsolutePath());
            response.setContentType("text/html;charset=utf-8");
            try (final PrintWriter pw = response.getWriter()) {
                pw.println("<script>");
                pw.println("alert('파일이 없습니다.');");
                pw.println("history.back();");
                pw.println("</script>");
                return;
            } catch (final IOException e) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
                throw new CommonException(e);
            }
        }
        try (final OutputStream out = response.getOutputStream(); final FileInputStream fis = new FileInputStream(file)) {
            this.logger.info("file.getPath() : {}", file.getPath());
            this.logger.info("file.getName() : {}", file.getName());

            if (MyFileUtils.isFileType(file, FileType.PDF)) {
                response.setContentType("application/pdf");
            } else {
                response.setContentType("application/download;charset=utf-8");

                response.setContentLength((int) file.length());

                String oriFileName = (String) model.get(AbstractDownloadView.DOWNLOAD_ORI_FILE_NAME);
                if (StringUtils.isEmpty(oriFileName)) {
                    oriFileName = MyFileUtils.getEncodedFileName(request, file.getName());
                }
                response.setHeader("Content-Disposition", "attachment; filename=\"" + MyFileUtils.getEncodedFileName(request, oriFileName) + "\";");
                response.setHeader("Content-Transfer-Encoding", "binary");
            }

            FileCopyUtils.copy(fis, out);
            out.flush();
        } catch (final IOException e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
    }
}
