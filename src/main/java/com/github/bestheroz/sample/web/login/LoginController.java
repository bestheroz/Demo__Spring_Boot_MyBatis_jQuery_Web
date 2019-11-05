package com.github.bestheroz.sample.web.login;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    //    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LoginService loginService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = {"/", "/sample/login/login.view"}, method = RequestMethod.GET)
    public String view() {
        if (MySessionUtils.getLoginVO(this.session) != null) {
            final String returnUrl = MySessionUtils.getAttribute(this.session, "returnUrl");
            this.session.removeAttribute("returnUrl");
            if (StringUtils.isEmpty(returnUrl) || StringUtils.equals(returnUrl, "null") || !StringUtils.endsWith(returnUrl, ".view")) {
                return "redirect:/sample/admin/menu/adminMenu.view";
            } else {
                return "redirect:" + returnUrl;
            }
        }
        return "/sample/login/login";
    }

    @RequestMapping(value = "/sample/login/loginProcess.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject loginProcess(final LoginVO vo) throws CommonException {
        this.loginService.loginProcess(vo, this.session);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/login/logout.view", method = RequestMethod.GET)
    public String logoutView() throws CommonException {
        MySessionUtils.logout(this.session);
        return "redirect:/sample/login/login.view";
    }
}
