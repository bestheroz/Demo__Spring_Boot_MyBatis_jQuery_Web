package com.github.bestheroz.sample.web.guide.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleGuideHtmlController {
    @RequestMapping(value = "/sample/guide/html/sampleGuideHtml.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/html/sampleGuideHtml";
    }
}
