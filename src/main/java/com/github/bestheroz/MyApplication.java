package com.github.bestheroz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.github.bestheroz")
public class MyApplication {
    public static void main(final String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
