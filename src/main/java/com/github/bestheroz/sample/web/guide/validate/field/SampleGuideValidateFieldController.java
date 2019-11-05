package com.github.bestheroz.sample.web.guide.validate.field;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleGuideValidateFieldController {
    @RequestMapping(value = "/sample/guide/validate/field/sampleGuideValidateField.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/validate/field/sampleGuideValidateField";
    }
}
