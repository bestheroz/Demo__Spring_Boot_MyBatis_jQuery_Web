package com.github.bestheroz.sample.web.guide.modal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleGuideModalController {
    @GetMapping(value = "/sample/guide/modal/sampleGuideModal.view")
    public String view() {
        return "/sample/guide/modal/sampleGuideModal";
    }
}
