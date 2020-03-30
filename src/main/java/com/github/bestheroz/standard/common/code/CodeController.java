package com.github.bestheroz.standard.common.code;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("common/code")
public class CodeController {
    @Resource private CodeService codeService;

    @PostMapping(value = "getCodeVOList.json")
    public List<CodeVO> getCodeVOList(@RequestParam(value = "groupCode") final String groupCode) {
        return this.codeService.getCodeVOList(groupCode);
    }
}
