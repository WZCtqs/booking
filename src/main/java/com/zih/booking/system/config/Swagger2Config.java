package com.zih.booking.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shahy
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 添加摘要信息(Docket)
     */
    @Bean
    public Docket controllerApi() {

        ParameterBuilder auth = new ParameterBuilder();
        ParameterBuilder language = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        auth.name("token").description("校验")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).defaultValue("").build();
        language.name("language").description("语言")
                .modelRef(new ModelRef("string")).parameterType("query")
                .required(false).defaultValue("zh").build();
        pars.add(auth.build());
        pars.add(language.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("订舱客户端_接口文档")
                        .description("-")
                        .version("版本号:1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zih.booking"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }
}
