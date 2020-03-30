package com.github.bestheroz.standard.common.taglibrary;

import com.github.bestheroz.standard.common.constant.CommonCode;
import com.github.bestheroz.standard.common.exception.BusinessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.servlet.jsp.tagext.TagSupport;
import java.text.MessageFormat;


@Slf4j
@Data
public class HtmlTag extends TagSupport {
    private static final long serialVersionUID = 491839523317391051L;
    private final StringBuilder sb = new StringBuilder();

    private String title;
    /**
     * Plugin JS, CSS
     */
    private String cookie;
    private String datetimePicker;
    private String fileDownloader;
    private String icon;
    private String handlebars;
    private String maxLength;
    private String modal;
    private String numberFormatter;
    private String paging;
    private String progressBar;
    private String popup;
    private String scrollUp;
    private String table;
    private String textEditor;
    private String validator;

    @Override
    public int doStartTag() {
        try {
            this.sb.setLength(0);
            this.sb.append("<!DOCTYPE html>");
            this.sb.append("<html lang=\"ko\">");
            this.sb.append("<head>");
            this.sb.append("<title>");
            if (StringUtils.isEmpty(this.title)) {
                this.sb.append("Bestheroz's Spring-Boot-Mybatis-jQuery Web Project ver.200327");
            } else {
                this.sb.append(this.title);
            }
            this.sb.append("</title>");
            this.sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
            this.sb.append("<meta http-equiv=\"Content-Type\" charset=\"UTF-8\" content=\"text/html; charset=utf-8\" />");
            this.sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\" />");
            this.sb.append("<link rel=\"shortcut icon\" href=\"data:image/x-icon;,\" type=\"image/x-icon\"> ");

            // <!-- 페이지 필수 공통 내용 START -->
            this.makeLinkTag("https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css");
            this.makeLinkTag("https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css");
            this.makeLinkTag(CommonCode.PATH_CSS + "/MyCommon.css");
            this.makeScriptTag("https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js");
            this.makeScriptTag("https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.15.0/umd/popper.min.js");
            this.makeScriptTag("https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js");
            this.makeScriptTag("https://cdn.jsdelivr.net/npm/lodash@4.17.15/lodash.min.js");
            this.makeScriptTag("https://cdn.jsdelivr.net/npm/moment@2.24.0/moment.min.js");
            this.makeScriptTag("https://cdn.jsdelivr.net/npm/moment@2.24.0/locale/ko.js");

            this.sb.append("<script>");
            this.sb.append(MessageFormat.format("const CONTEXT_PATH = ''{0}'';", CommonCode.CONTEXT_PATH));
            this.sb.append("</script>");

            this.makeScriptTag(CommonCode.PATH_JS + "/MyCommon.js");
            this.makeScriptTag(CommonCode.PATH_JS + "/MyKeyup.js");
            this.makeScriptTag(CommonCode.PATH_JS + "/MyAjax.js");
            // // <!-- 페이지 필수 공통 내용 END -->

            // 이놈이 사이즈가 꾀 커서 다른 plugin의 property name을 먹어버린다. 그래서 상단에 선언
            if (StringUtils.equals(this.table, CommonCode.YES)) {
                this.makeLinkTag("https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css");
                this.makeLinkTag("https://cdn.datatables.net/buttons/1.6.1/css/buttons.bootstrap4.min.css");
                this.makeLinkTag("https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css");
                this.makeLinkTag("https://cdn.datatables.net/fixedcolumns/3.3.0/css/fixedColumns.bootstrap4.min.css");
                this.makeLinkTag("https://cdn.datatables.net/fixedheader/3.1.6/css/fixedHeader.bootstrap4.min.css");
                this.makeLinkTag("https://cdn.datatables.net/select/1.3.1/css/select.bootstrap4.min.css");
                this.makeLinkTag(CommonCode.PATH_CSS + "/MyDataTables.css");

                this.makeScriptTag("https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js");
                this.makeScriptTag("https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js");
                this.makeScriptTag("https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js");
                this.makeScriptTag("https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js");
                this.makeScriptTag("https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js");
                this.makeScriptTag("https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js");
                this.makeScriptTag("https://cdn.datatables.net/buttons/1.6.1/js/buttons.bootstrap4.min.js");
                this.makeScriptTag("https://cdn.datatables.net/buttons/1.6.1/js/buttons.colVis.min.js");
                this.makeScriptTag("https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js");
                this.makeScriptTag("https://cdn.datatables.net/buttons/1.6.1/js/buttons.print.min.js");
                this.makeScriptTag("https://cdn.datatables.net/fixedcolumns/3.3.0/js/dataTables.fixedColumns.min.js");
                this.makeScriptTag("https://cdn.datatables.net/fixedheader/3.1.6/js/dataTables.fixedHeader.min.js");
                this.makeScriptTag("https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js");
                this.makeScriptTag(CommonCode.PATH_JS + "/MyDataTables.js");
            }
            if (StringUtils.equals(this.cookie, CommonCode.YES)) {
                this.makeScriptTag(CommonCode.PATH_JS + "/MyCookie.js");
            }
            if (!StringUtils.equals(this.datetimePicker, CommonCode.NO)) {
                this.makeLinkTag("https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.1.2/css/tempusdominus-bootstrap-4.min.css");
                this.makeLinkTag(CommonCode.PATH_CSS + "/MyDatetimePicker.css");
                this.makeScriptTag("https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.1.2/js/tempusdominus-bootstrap-4.min.js");
                this.makeScriptTag(CommonCode.PATH_JS + "/MyDatetimePicker.js");
            }
            if (StringUtils.equals(this.fileDownloader, CommonCode.YES)) {
                this.makeScriptTag("https://cdn.jsdelivr.net/npm/jquery-file-download@1.4.6/src/Scripts/jquery.fileDownload.js");
            }
            if (StringUtils.equals(this.handlebars, CommonCode.YES)) {
                this.makeScriptTag("https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.3/handlebars.min.js");
                this.makeScriptTag(CommonCode.PATH_JS + "/MyHandlebars.js");
            }
            if (!StringUtils.equals(this.icon, CommonCode.NO)) {
                this.makeLinkTag("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css");
            }
            if (!StringUtils.equals(this.maxLength, CommonCode.NO)) {
                this.makeScriptTag("https://cdnjs.cloudflare.com/ajax/libs/bootstrap-maxlength/1.9.0/bootstrap-maxlength.min.js");
                this.makeScriptTag(CommonCode.PATH_JS + "/MyMaxlength.js");
            }
            if (StringUtils.equals(this.modal, CommonCode.YES)) {
                this.makeLinkTag(CommonCode.PATH_CSS + "/MyModal.css");
                this.makeScriptTag(CommonCode.PATH_JS + "/MyModal.js");
            }
            if (StringUtils.equals(this.numberFormatter, CommonCode.YES)) {
                this.makeScriptTag("https://cdn.jsdelivr.net/npm/jquery-number@2.1.6/jquery.number.min.js");
            }
            if (StringUtils.equals(this.paging, CommonCode.YES)) {
                this.makeScriptTag(CommonCode.PATH_JS + "/MyPaging.js");
            }
            if (!StringUtils.equals(this.popup, CommonCode.NO)) {
                this.makeScriptTag(CommonCode.PATH_PLUGIN + "/jquery-popupwindow/jquery.popupwindow.js");
            }
            if (!StringUtils.equals(this.progressBar, CommonCode.NO)) {
                this.makeLinkTag("https://cdnjs.cloudflare.com/ajax/libs/pace/1.0.2/themes/black/pace-theme-corner-indicator.min.css");
                this.makeLinkTag(CommonCode.PATH_CSS + "/MyPace.css");
                this.makeScriptTag("https://cdnjs.cloudflare.com/ajax/libs/pace/1.0.2/pace.min.js");
            }
            if (!StringUtils.equals(this.scrollUp, CommonCode.NO)) {
                this.makeLinkTag(CommonCode.PATH_CSS + "/MyScrollUp.css");
                this.makeScriptTag("https://cdnjs.cloudflare.com/ajax/libs/scrollup/2.4.1/jquery.scrollUp.min.js");
                this.makeScriptTag(CommonCode.PATH_JS + "/MyScrollUp.js");
            }

            if (StringUtils.equals(this.textEditor, CommonCode.YES)) {
                this.makeLinkTag("https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote-bs4.css");
                this.makeLinkTag(CommonCode.PATH_CSS + "/MyTextEditor.css");
                this.makeScriptTag("https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote-bs4.js");
                this.makeScriptTag("https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.16/lang/summernote-ko-KR.min.js");
                this.makeScriptTag(CommonCode.PATH_JS + "/MyTextEditor.js");
            }

            if (StringUtils.equals(this.validator, CommonCode.YES)) {
                this.makeScriptTag("https://cdnjs.cloudflare.com/ajax/libs/validator/13.0.0/validator.min.js");
                this.makeScriptTag("https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.9.1/parsley.min.js");
                this.makeScriptTag("https://cdn.jsdelivr.net/npm/parsleyjs@2.9.1/dist/i18n/ko.js");
                this.makeScriptTag(CommonCode.PATH_JS + "/MyValidator.js");
            }

            this.makeLinkTag("https://cdnjs.cloudflare.com/ajax/libs/outdated-browser/1.1.5/outdatedbrowser.min.css");
            this.makeScriptTag("https://cdnjs.cloudflare.com/ajax/libs/outdated-browser/1.1.5/outdatedbrowser.min.js");
            this.makeScriptTag(CommonCode.PATH_JS + "/MyOutdatedBrowser.js");

            this.sb.append("</head>");
            this.sb.append("<body class=\"d-flex flex-column h-100\">");
            this.pageContext.getOut().print(this.sb.toString());
            return EVAL_BODY_INCLUDE;
        } catch (final Throwable e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new BusinessException(e);
        }
    }

    @Override
    public int doEndTag() {
        try {
            this.pageContext.getOut().print("</body></html>");
            return SKIP_PAGE;
        } catch (final Throwable e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new BusinessException(e);
        }
    }

    private void makeScriptTag(final String path) {
        this.sb.append("<script src=\"").append(path).append("\"></script>");
    }

    private void makeLinkTag(final String path) {
        this.sb.append("<link rel=\"stylesheet\" href=\"").append(path).append("\" />");
    }
}
