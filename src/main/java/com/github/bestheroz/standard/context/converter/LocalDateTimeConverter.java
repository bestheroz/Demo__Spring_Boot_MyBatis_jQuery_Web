package com.github.bestheroz.standard.context.converter;

import com.github.bestheroz.standard.common.util.MyDateUtils;
import org.joda.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;

public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(final String arg0) {
        return MyDateUtils.getLocalDateTimeIgnoreException(arg0);
    }
}
