package com.github.bestheroz.standard.common.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommonExceptionController {
    @GetMapping(value = {"/common/exception/error.view"})
    public String errorView(final HttpSession session) {
        session.removeAttribute("returnUrl");
        return "/common/exception/error";
    }

    @GetMapping(value = "/common/exception/error404.view")
    public String error404View(final HttpSession session) {
        session.removeAttribute("returnUrl");
        return "/common/exception/error404";
    }

}
