package com.github.bestheroz.standard.common.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

@RestController
public class CommonExceptionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = {"/common/exception/error"})
    public Json errorView(final Throwable e) {
        this.logger.warn(ExceptionUtils.getStackTrace(e));
        return new Json(new CommonException(e).getJsonObject().toString());
    }
}
