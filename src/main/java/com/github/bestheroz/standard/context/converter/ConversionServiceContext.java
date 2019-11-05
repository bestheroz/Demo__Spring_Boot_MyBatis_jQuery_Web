package com.github.bestheroz.standard.context.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;

@Configuration
public class ConversionServiceContext {
    @Bean(name = "conversionService")
    @Autowired(required = false)
    public ConversionService getConversionService(final Set<Converter<?, ?>> set) {
        final ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        set.add(new DateTimeConverter());
        set.add(new LocalDateTimeConverter());
        conversionServiceFactoryBean.setConverters(set);
        conversionServiceFactoryBean.afterPropertiesSet();
        return conversionServiceFactoryBean.getObject();
    }
}
