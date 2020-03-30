package com.github.bestheroz.standard.common.code;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CodeService {
    @Resource private CodeDAO codeDAO;

    public List<CodeVO> getCodeVOList(final String groupCode) {
        return this.codeDAO.getCodeVOList(groupCode);
    }

    public JsonObject getCodeVoListToJsonObject(final String groupCode) {
        final JsonObject jsonObject = new JsonObject();
        for (final CodeVO vo : this.getCodeVOList(groupCode)) {
            jsonObject.addProperty(vo.getValue(), vo.getLabel());
        }
        return jsonObject;
    }
}
