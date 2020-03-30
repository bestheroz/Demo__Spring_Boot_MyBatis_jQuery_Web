package com.github.bestheroz.standard.context.converter;

import com.github.bestheroz.standard.common.util.DateUtils;
import org.joda.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(final String arg0) {
        return DateUtils.getLocalDateTimeIgnoreException(arg0);
    }
}
