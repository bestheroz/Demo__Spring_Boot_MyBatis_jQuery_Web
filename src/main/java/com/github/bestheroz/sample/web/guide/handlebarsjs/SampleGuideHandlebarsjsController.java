package com.github.bestheroz.sample.web.guide.handlebarsjs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleGuideHandlebarsjsController {
    @RequestMapping(value = "/sample/guide/handlebarsjs/sampleGuideHandlebarsjs.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/handlebarsjs/sampleGuideHandlebarsjs";
    }
}
