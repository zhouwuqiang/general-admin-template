package com.java.general.config.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * description :
 *
 * @author : zhouqiang
 * @date : Created in 2019/10/10 下午7:42
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("")
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.java"))
            .paths(PathSelectors.any())
            .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("**平台对外接口")
            .description("1.提供**后台使用的接口 2.提供对其他服务调用的服务")
            .contact(new Contact("", "", ""))
            .version("1.0")
            .build();
    }
}
