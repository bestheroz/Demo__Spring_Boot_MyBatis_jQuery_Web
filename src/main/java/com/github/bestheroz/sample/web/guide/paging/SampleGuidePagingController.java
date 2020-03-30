package com.github.bestheroz.sample.web.guide.paging;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class SampleGuidePagingController {
    @Resource SampleGuidePagingService sampleGuidePagingService;

    @GetMapping(value = "/sample/guide/paging/sampleGuidePaging.view")
    public String view() {
        return "/sample/guide/paging/sampleGuidePaging";
    }

    @PostMapping(value = "/sample/guide/paging/sampleGuidePaging.json")
    @ResponseBody
    public JsonObject getSampleMenuMstVOList() {
        return this.sampleGuidePagingService.getSampleMenuMstVOList();
    }
}
