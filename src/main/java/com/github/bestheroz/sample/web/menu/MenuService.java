package com.github.bestheroz.sample.web.menu;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MenuService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MenuDAO menuDAO;

    public JsonArray getMenuVOObject(final JsonObject param, final boolean isNotLogined) throws CommonException {
        if (isNotLogined) {
            this.logger.warn(CommonException.EXCEPTION_FAIL_NOT_ALLOWED_MEMBER.getJsonObject().toString());
            throw CommonException.EXCEPTION_FAIL_NOT_ALLOWED_MEMBER;
        }

        final JsonObject temp = new JsonObject();
        for (final MenuVO menuVO : this.menuDAO.getMenuVOList(param)) {
            if (menuVO.getLvl() == 2) {
                temp.add(menuVO.getMenuId().toString(), MyMapperUtils.writeObjectAsJsonElement(menuVO));
            } else if (menuVO.getLvl() == 3) {
                final JsonObject tempJsonObject = temp.get(menuVO.getParMenuId().toString()).getAsJsonObject();
                final JsonArray children;
                if (tempJsonObject.has("children")) {
                    children = tempJsonObject.get("children").getAsJsonArray();
                } else {
                    children = new JsonArray();
                }
                children.add(MyMapperUtils.writeObjectAsJsonElement(menuVO));
                tempJsonObject.add("children", children);
            }
        }
        final JsonArray result = new JsonArray();
        for (final Map.Entry<String, JsonElement> entry : temp.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}
