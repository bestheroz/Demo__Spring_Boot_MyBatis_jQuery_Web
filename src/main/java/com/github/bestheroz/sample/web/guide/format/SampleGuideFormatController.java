package com.github.bestheroz.sample.web.guide.format;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleGuideFormatController {
    @RequestMapping(value = "/sample/guide/format/sampleGuideFormat.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/format/sampleGuideFormat";
    }
}
