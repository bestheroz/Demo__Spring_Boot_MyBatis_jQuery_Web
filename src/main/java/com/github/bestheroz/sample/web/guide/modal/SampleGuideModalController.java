package com.github.bestheroz.sample.web.guide.modal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleGuideModalController {
    @RequestMapping(value = "/sample/guide/modal/sampleGuideModal.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/modal/sampleGuideModal";
    }
}
