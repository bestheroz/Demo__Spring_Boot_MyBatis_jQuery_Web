package com.github.bestheroz.standard.common.valuelabel;

import com.github.bestheroz.standard.common.exception.CommonException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ValueLabelController {
    @Resource ValueLabelService valueLabelService;

    @PostMapping(value = "/common/valuelabel/getValueLabelVOList.json")
    public List<ValueLabelVO> getValueLabelVOList(@RequestParam("groupCode") final String groupCode) throws CommonException {
        return this.valueLabelService.getValueLabelVOList(groupCode);
    }
}
