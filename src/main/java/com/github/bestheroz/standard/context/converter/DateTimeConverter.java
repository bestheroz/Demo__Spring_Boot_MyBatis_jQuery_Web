package com.github.bestheroz.standard.context.converter;

import com.github.bestheroz.standard.common.util.DateUtils;
import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateTimeConverter implements Converter<String, DateTime> {
    @Override
    public DateTime convert(final String arg0) {
        return DateUtils.getDateTimeIgnoreException(arg0);
    }
}
