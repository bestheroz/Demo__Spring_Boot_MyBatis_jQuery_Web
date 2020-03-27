package com.github.bestheroz.sample.web.guide.validate.value;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleGuideValidateValueController {
    @GetMapping(value = "/sample/guide/validate/value/sampleGuideValidateValue.view")
    public String view() {
        return "/sample/guide/validate/value/sampleGuideValidateValue";
    }
}
