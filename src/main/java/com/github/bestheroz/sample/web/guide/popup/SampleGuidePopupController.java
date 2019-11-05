package com.github.bestheroz.sample.web.guide.popup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleGuidePopupController {
    @RequestMapping(value = "/sample/guide/popup/sampleGuidePopup.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/popup/sampleGuidePopup";
    }
}
