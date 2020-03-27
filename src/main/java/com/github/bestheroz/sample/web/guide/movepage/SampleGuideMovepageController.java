package com.github.bestheroz.sample.web.guide.movepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleGuideMovepageController {
    @GetMapping(value = "/sample/guide/movepage/sampleGuideMovepage.view")
    public String view() {
        return "/sample/guide/movepage/sampleGuideMovepage";
    }
}
