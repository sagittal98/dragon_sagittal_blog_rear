package com.dragon.sagittal.blog.common.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger API 配置类，实现API自动生成页面并能通过swagger进行实时测试
 *
 * @author ChunYu Sagittal
 * @date 2021/8/31
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("DragonSagittalBlogAPI")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("Dragon Sagittal Blog API文档")
                .description("本文档是Dragon Sagittal BlogAPI接口文档")
                .version("1.0")
                .contact(new Contact("ChunYu Sagittal", "https://www.chunyu.dragon.com", "863524199@qq.com"))
                .build();
    }

}
