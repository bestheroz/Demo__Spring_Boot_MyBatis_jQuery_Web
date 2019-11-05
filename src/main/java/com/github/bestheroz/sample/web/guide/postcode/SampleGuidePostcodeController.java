package com.github.bestheroz.sample.web.guide.postcode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleGuidePostcodeController {
    @RequestMapping(value = "/sample/guide/postcode/sampleGuidePostcode.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/postcode/sampleGuidePostcode";
    }
}
