package com.github.bestheroz.sample.web.guide.validate.field;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleGuideValidateFieldController {
    @GetMapping(value = "/sample/guide/validate/field/sampleGuideValidateField.view")
    public String view() {
        return "/sample/guide/validate/field/sampleGuideValidateField";
    }
}
