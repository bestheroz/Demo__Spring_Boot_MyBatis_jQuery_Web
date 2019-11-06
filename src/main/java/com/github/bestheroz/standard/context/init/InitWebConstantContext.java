package com.github.bestheroz.standard.context.init;

import com.github.bestheroz.standard.common.util.MyDateUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import java.util.TimeZone;

@Configuration
public class InitWebConstantContext {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    public void setConstant(final ServletContext servletContext) throws IllegalArgumentException {
        DateTimeZone.setDefault(MyDateUtils.TIME_ZONE_ASIA_SEOUL);
        TimeZone.setDefault(MyDateUtils.TIME_ZONE_ASIA_SEOUL.toTimeZone());
        servletContext.setAttribute("TIME_ZONE_ASIA_SEOUL", MyDateUtils.TIME_ZONE_ASIA_SEOUL.getID());
        servletContext.setAttribute("LOCALE_KOREAN", MyDateUtils.LOCALE_KOREAN.toString());
        this.logger.info("DateTimeZone/TimeZone.setDefault(\"{}\"); - Complete", MyDateUtils.TIME_ZONE_ASIA_SEOUL.getID());
        this.logger.info("\n{}\nThis Framework is managed by bestheroz.\nIf you have any questions, send me feedback.\nE-mail: bestheroz@gmail.com\ngithub: https://github.com/bestheroz\n{}",
                StringUtils.repeat("=", 80),
                StringUtils.repeat("=", 80));
    }
}
