package com.github.bestheroz.standard.common.taglibrary;

import com.github.bestheroz.sample.web.login.LoginVO;
import com.github.bestheroz.sample.web.menu.MenuService;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.util.AccessBeanUtils;
import com.github.bestheroz.standard.common.util.MapperUtils;
import com.github.bestheroz.standard.common.util.SessionUtils;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.File;
import java.nio.charset.StandardCharsets;

@Slf4j
public class MenuTag extends TagSupport {
    public static final String MENU_TAG = "MENU_TAG";
    private static final long serialVersionUID = -8260051129668642050L;

    @Override
    public int doStartTag() {
        final HttpSession session = ((HttpServletRequest) this.pageContext.getRequest()).getSession();

        try {
            if (SessionUtils.getAttribute(MENU_TAG) == null) {
                final StringBuilder bodyHtml = new StringBuilder(1024 * 100);
                final LoginVO loginVO = SessionUtils.getLoginVO();
                if (loginVO != null) {
                    final JsonObject param = new JsonObject();
                    final Integer memberType = Integer.parseInt(loginVO.getMemberType());
                    if (memberType != null && memberType.intValue() >= 800) {
                        param.addProperty("power", memberType);
                        bodyHtml.append("<script> const menuData = ")
                                .append(MapperUtils.toString(AccessBeanUtils.getBean(MenuService.class).getMenuVOObject(param, SessionUtils.isNotLoggedIn())))
                                .append("</script>");
                        bodyHtml.append("<script> const menuMemberName = ").append(loginVO.getMemberName()).append("</script>");
                    }
                    bodyHtml.append(IOUtils.toString(new File(session.getServletContext().getRealPath("/WEB-INF/views/common/menu.html")).toURI(), StandardCharsets.UTF_8));
                }
                SessionUtils.setAttribute(MENU_TAG, bodyHtml.toString());
            }
            this.pageContext.getOut().print(session.getAttribute(MENU_TAG));
        } catch (final Throwable e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new BusinessException(e);
        }
        return SKIP_PAGE;
    }
}
