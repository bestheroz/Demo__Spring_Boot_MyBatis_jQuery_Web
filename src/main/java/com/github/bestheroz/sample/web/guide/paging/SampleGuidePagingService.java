package com.github.bestheroz.sample.web.guide.paging;

import com.github.bestheroz.sample.web.admin.menu.AdminMenuDAO;
import com.github.bestheroz.sample.web.admin.menu.AdminMenuVO;
import com.github.bestheroz.standard.common.util.MapperUtils;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SampleGuidePagingService {
    @Resource AdminMenuDAO adminMenuDAO;

    public JsonObject getSampleMenuMstVOList() {
        final int nextPage = 5;
        final int itemPerPage = 5;
        final JsonObject result = new JsonObject();
        result.addProperty("nextPage", nextPage);
        result.addProperty("itemPerPage", itemPerPage);
        final List<AdminMenuVO> list = this.adminMenuDAO.getSampleMenuMstVOList(null);
        result.addProperty("totalItemCount", list.size());
        result.add("list", MapperUtils.toJsonElement(list));
        return result;
    }
}
