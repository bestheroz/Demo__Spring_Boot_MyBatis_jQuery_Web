package com.github.bestheroz.standard.common.taglibrary;

import com.github.bestheroz.sample.web.login.LoginVO;
import com.github.bestheroz.sample.web.menu.MenuService;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyAccessBeanUtils;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class MenuTag extends TagSupport {
    public static final String MENU_TAG = "MENU_TAG";
    private static final long serialVersionUID = -8260051129668642050L;
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuTag.class);

    @Override
    public int doStartTag() {
        final HttpSession session = ((HttpServletRequest) this.pageContext.getRequest()).getSession();

        try {
            if (MySessionUtils.getAttribute(session, MENU_TAG) == null) {
                final StringBuilder bodyHtml = new StringBuilder(1024 * 100);
                final LoginVO loginVO = MySessionUtils.getLoginVO(session);
                if (loginVO != null) {
                    final JsonObject param = new JsonObject();
                    final Integer memberType = Integer.parseInt(loginVO.getMemberType());
                    if (memberType != null && memberType.intValue() >= 800) {
                        param.addProperty("power", memberType);
                        bodyHtml.append("<script> const menuData = ")
                                .append(MyMapperUtils.writeObjectAsString(MyAccessBeanUtils.getBean(MenuService.class).getMenuVOObject(param, MySessionUtils.isNotLogined(session))))
                                .append("</script>");
                        bodyHtml.append("<script> const menuMemberName = ").append(loginVO.getMemberName()).append("</script>");
                    }
                    bodyHtml.append(IOUtils.toString(new File(session.getServletContext().getRealPath("/WEB-INF/views/common/menu.html")).toURI(), StandardCharsets.UTF_8));
                }
                MySessionUtils.setAttribute(session, MENU_TAG, bodyHtml.toString());
            }
            this.pageContext.getOut().print(session.getAttribute(MENU_TAG));
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
        return SKIP_PAGE;
    }
}
