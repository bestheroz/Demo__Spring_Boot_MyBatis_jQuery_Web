package com.github.bestheroz.sample.web.login;

import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.util.SessionUtils;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LoginController {
    @Resource LoginService loginService;

    @GetMapping(value = {"/", "/sample/login/login.view"})
    public String view() {
        if (SessionUtils.isLoggedIn()) {
            final String returnUrl = SessionUtils.getAttribute("returnUrl");
            SessionUtils.removeAttribute("returnUrl");
            if (StringUtils.isEmpty(returnUrl) || StringUtils.equals(returnUrl, "null") || !StringUtils.endsWith(returnUrl, ".view")) {
                return "redirect:/sample/admin/menu/adminMenu.view";
            } else {
                return "redirect:" + returnUrl;
            }
        }
        return "/sample/login/login";
    }

    @PostMapping(value = "/sample/login/loginProcess.json")
    @ResponseBody
    public JsonObject loginProcess(final LoginVO vo) {
        this.loginService.loginProcess(vo);
        return BusinessException.SUCCESS_NORMAL.getJsonObject();
    }

    @GetMapping(value = "/sample/login/logout.view")
    public String logoutView() {
        SessionUtils.logout();
        return "redirect:/sample/login/login.view";
    }
}
