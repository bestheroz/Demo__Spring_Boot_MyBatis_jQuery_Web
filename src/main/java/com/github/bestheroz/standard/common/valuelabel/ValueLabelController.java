package com.github.bestheroz.standard.common.valuelabel;

import com.github.bestheroz.standard.common.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ValueLabelController {
    @Autowired
    private ValueLabelService valueLabelService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/common/valuelabel/getValueLabelVOList.json", method = RequestMethod.POST)
    @ResponseBody
    public List<ValueLabelVO> getValueLabelVOList(@RequestParam("groupCode") final String groupCode) throws CommonException {
        return this.valueLabelService.getValueLabelVOList(groupCode);
    }
}
