package com.github.bestheroz.sample.web.guide.postcode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleGuidePostcodeController {
    @GetMapping(value = "/sample/guide/postcode/sampleGuidePostcode.view")
    public String view() {
        return "/sample/guide/postcode/sampleGuidePostcode";
    }
}
