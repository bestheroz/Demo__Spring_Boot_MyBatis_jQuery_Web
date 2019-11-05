package com.github.bestheroz.sample.web.guide.ajax;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleGuideAjaxController {
    @RequestMapping(value = "/sample/guide/ajax/sampleGuideAjax.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/ajax/sampleGuideAjax";
    }

    @RequestMapping(value = "/sample/guide/ajax/sampleHtml.view", method = RequestMethod.POST)
    public String sampleHtml() {
        return "/sample/guide/ajax/sampleHtml";
    }
}
