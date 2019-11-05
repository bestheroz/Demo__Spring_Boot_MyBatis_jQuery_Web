package com.github.bestheroz.sample.web.guide.texteditor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleGuideTexteditorController {
    @RequestMapping(value = "/sample/guide/texteditor/sampleGuideTexteditor.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/texteditor/sampleGuideTexteditor";
    }
}
