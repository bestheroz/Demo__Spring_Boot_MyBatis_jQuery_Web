package com.github.bestheroz.sample.web.guide.handlebarsjs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleGuideHandlebarsjsController {
    @GetMapping(value = "/sample/guide/handlebarsjs/sampleGuideHandlebarsjs.view")
    public String view() {
        return "/sample/guide/handlebarsjs/sampleGuideHandlebarsjs";
    }
}
