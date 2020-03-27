package com.github.bestheroz.sample.web.guide.popup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleGuidePopupController {
    @GetMapping(value = "/sample/guide/popup/sampleGuidePopup.view")
    public String view() {
        return "/sample/guide/popup/sampleGuidePopup";
    }
}
