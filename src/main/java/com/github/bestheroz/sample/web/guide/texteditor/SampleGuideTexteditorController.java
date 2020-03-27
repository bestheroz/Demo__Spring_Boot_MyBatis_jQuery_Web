package com.github.bestheroz.sample.web.guide.texteditor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleGuideTexteditorController {
    @GetMapping(value = "/sample/guide/texteditor/sampleGuideTexteditor.view")
    public String view() {
        return "/sample/guide/texteditor/sampleGuideTexteditor";
    }
}
