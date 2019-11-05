package com.github.bestheroz.sample.web.guide.paging;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleGuidePagingController {

    @Autowired
    private SampleGuidePagingService sampleGuidePagingService;

    @RequestMapping(value = "/sample/guide/paging/sampleGuidePaging.view", method = RequestMethod.GET)
    public String view() {
        return "/sample/guide/paging/sampleGuidePaging";
    }

    @RequestMapping(value = "/sample/guide/paging/sampleGuidePaging.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject getSampleMenuMstVOList() throws CommonException {
        return this.sampleGuidePagingService.getSampleMenuMstVOList();
    }
}
