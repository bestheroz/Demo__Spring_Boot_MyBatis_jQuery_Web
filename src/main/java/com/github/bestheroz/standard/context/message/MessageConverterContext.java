package com.github.bestheroz.standard.context.message;

import com.github.bestheroz.standard.common.util.MyMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class MessageConverterContext {
    @Autowired(required = false)
    private RequestMappingHandlerAdapter adapter;

    @PostConstruct
    public void initStuff() {
        final List<HttpMessageConverter<?>> messageConverters = this.adapter.getMessageConverters();
        messageConverters.clear();

        final GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(MyMapperUtils.getGsonObject());
        messageConverters.add(gsonHttpMessageConverter);

        final StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        messageConverters.add(stringHttpMessageConverter);

        this.adapter.setMessageConverters(messageConverters);
    }
}
