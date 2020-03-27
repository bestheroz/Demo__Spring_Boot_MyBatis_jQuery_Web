package com.github.bestheroz.sample.web.guide.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleGuideHtmlController {
    @GetMapping(value = "/sample/guide/html/sampleGuideHtml.view")
    public String view() {
        return "/sample/guide/html/sampleGuideHtml";
    }
}
