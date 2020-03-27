package com.github.bestheroz.standard.common.valuelabel;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ValueLabelService {
    @Resource ValueLabelDAO valueLabelDAO;

    public List<ValueLabelVO> getValueLabelVOList(final String groupCode) throws CommonException {
        return this.valueLabelDAO.getValueLabelVOList(groupCode);
    }

    public JsonObject getValueLabelVoListToJsonObject(final String groupCode) throws CommonException {
        final JsonObject jsonObject = new JsonObject();
        for (final ValueLabelVO vo : this.getValueLabelVOList(groupCode)) {
            jsonObject.addProperty(vo.getValue(), vo.getLabel());
        }
        return jsonObject;
    }
}
