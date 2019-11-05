package com.github.bestheroz.standard.context.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // SWAGGER API 공통 메세지
    public static final String SWAGGER_COMMON_200_MESSAGE =
            "{<br/>&nbsp;&nbsp;responseCode&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : 응답코드(Text)<br/>&nbsp;&nbsp;responseMessage&nbsp;&nbsp; : 응답메세지(Text)<br/>&nbsp;&nbsp;responseData&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : 응답데이터 (Json)<br/>&nbsp;&nbsp;additionalMessage : 추가메세지(Text)<br/>}";

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.bestheroz"))
                .paths(PathSelectors.any())
                .build().apiInfo(this.getApiInfo())
                .globalOperationParameters(
                        Collections.singletonList(new ParameterBuilder()
                                .name("Authorization")
                                .description("Token Key of User")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .defaultValue("freepass*.*").required(true).build()))
                ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("bestheroz's API Platform").termsOfServiceUrl("https://github.com/bestheroz").version("191104")
                .license("Apache License Version 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0").build();
    }
}
