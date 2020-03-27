package com.github.bestheroz.sample.web.guide.ajax;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SampleGuideAjaxController {
    @GetMapping(value = "/sample/guide/ajax/sampleGuideAjax.view")
    public String view() {
        return "/sample/guide/ajax/sampleGuideAjax";
    }

    @PostMapping(value = "/sample/guide/ajax/sampleHtml.view")
    public String sampleHtml() {
        return "/sample/guide/ajax/sampleHtml";
    }
}
