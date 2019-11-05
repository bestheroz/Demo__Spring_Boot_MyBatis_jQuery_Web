package com.github.bestheroz.standard.context.converter;

import com.github.bestheroz.standard.common.util.MyDateUtils;
import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;

public class DateTimeConverter implements Converter<String, DateTime> {
    @Override
    public DateTime convert(final String arg0) {
        return MyDateUtils.getDateTimeIgnoreException(arg0);
    }
}
