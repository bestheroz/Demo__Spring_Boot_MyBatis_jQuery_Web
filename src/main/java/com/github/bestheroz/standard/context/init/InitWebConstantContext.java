package com.github.bestheroz.standard.context.init;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;

@Slf4j
@Configuration
public class InitWebConstantContext {
    @Autowired
    public void setConstant(final ServletContext servletContext) throws IllegalArgumentException {
//        DateTimeZone.setDefault(MyDateUtils.TIME_ZONE_ASIA_SEOUL);
//        TimeZone.setDefault(MyDateUtils.TIME_ZONE_ASIA_SEOUL.toTimeZone());
//        servletContext.setAttribute("TIME_ZONE_ASIA_SEOUL", MyDateUtils.TIME_ZONE_ASIA_SEOUL.getID());
//        servletContext.setAttribute("LOCALE_KOREAN", MyDateUtils.LOCALE_KOREAN.toString());
//        log.info("DateTimeZone/TimeZone.setDefault(\"{}\"); - Complete", servletContext.getAttribute("TIME_ZONE_ASIA_SEOUL"));
        log.info("\n{}\nThis Framework is managed by bestheroz.\nIf you have any questions, send me feedback.\nE-mail: bestheroz@gmail.com\ngithub: https://github.com/bestheroz\n{}",
                StringUtils.repeat("=", 80),
                StringUtils.repeat("=", 80));
    }
}
