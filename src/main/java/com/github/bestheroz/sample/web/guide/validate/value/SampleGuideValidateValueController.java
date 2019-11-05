package com.github.bestheroz.sample.web.guide.validate.value;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleGuideValidateValueController {
    @RequestMapping(value = "/sample/guide/validate/value/sampleGuideValidateValue.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/validate/value/sampleGuideValidateValue";
    }
}
