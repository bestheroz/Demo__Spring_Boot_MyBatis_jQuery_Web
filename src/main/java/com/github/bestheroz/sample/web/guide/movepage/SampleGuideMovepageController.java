package com.github.bestheroz.sample.web.guide.movepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleGuideMovepageController {
    @RequestMapping(value = "/sample/guide/movepage/sampleGuideMovepage.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/movepage/sampleGuideMovepage";
    }
}
