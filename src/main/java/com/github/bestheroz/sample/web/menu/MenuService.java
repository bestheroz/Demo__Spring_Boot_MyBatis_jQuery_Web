package com.github.bestheroz.sample.web.menu;

import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.util.MapperUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Slf4j
public class MenuService {
    @Resource MenuDAO menuDAO;

    public JsonArray getMenuVOObject(final JsonObject param, final boolean isNotLoggedIn) {
        if (isNotLoggedIn) {
            log.warn(BusinessException.FAIL_NOT_ALLOWED_MEMBER.getJsonObject().toString());
            throw BusinessException.FAIL_NOT_ALLOWED_MEMBER;
        }

        final JsonObject temp = new JsonObject();
        for (final MenuVO menuVO : this.menuDAO.getMenuVOList(param)) {
            if (menuVO.getLvl() == 2) {
                temp.add(menuVO.getMenuId().toString(), MapperUtils.toJsonElement(menuVO));
            } else if (menuVO.getLvl() == 3) {
                final JsonObject tempJsonObject = temp.get(menuVO.getParMenuId().toString()).getAsJsonObject();
                final JsonArray children;
                if (tempJsonObject.has("children")) {
                    children = tempJsonObject.get("children").getAsJsonArray();
                } else {
                    children = new JsonArray();
                }
                children.add(MapperUtils.toJsonElement(menuVO));
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
