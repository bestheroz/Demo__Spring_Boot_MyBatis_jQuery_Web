package com.github.bestheroz.sample.web.guide.datetimepicker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleGuideDatetimepickerController {
    @GetMapping(value = "/sample/guide/datetimepicker/sampleGuideDatetimepicker.view")
    public String view() {
        return "/sample/guide/datetimepicker/sampleGuideDatetimepicker";
    }
}
