package com.github.bestheroz.standard.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyAccessBeanUtils {
    private static MyAccessBeanUtils instance;

    @Resource ApplicationContext applicationContext;

    private MyAccessBeanUtils() {
        if (instance == null) {
            instance = this;
        }
    }

    public static <T> T getBean(final Class<T> clazz) {
        return instance.applicationContext.getBean(clazz);
    }
}
