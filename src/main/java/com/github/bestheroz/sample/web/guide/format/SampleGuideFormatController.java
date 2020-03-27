package com.github.bestheroz.sample.web.guide.format;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleGuideFormatController {
    @GetMapping(value = "/sample/guide/format/sampleGuideFormat.view")
    public String view() {
        return "/sample/guide/format/sampleGuideFormat";
    }
}
