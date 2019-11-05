package com.github.bestheroz.sample.web.guide.datetimepicker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleGuideDatetimepickerController {
    @RequestMapping(value = "/sample/guide/datetimepicker/sampleGuideDatetimepicker.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/datetimepicker/sampleGuideDatetimepicker";
    }
}
