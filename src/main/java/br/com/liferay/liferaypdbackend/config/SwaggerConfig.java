package br.com.liferay.liferaypdbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Bean
    public Docket liferayEvpApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.liferay.liferaypdbackend"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Liferay EVP REST API",
                "REST API for Liferay Employee Volunteer Program developed on Porto Digital technological residency",
                "1.0",
                "Terms of Service",
                new Contact("Squad Liferay Senac", "https://github.com/Liferay-Porto-Digital", "liferayportodigital@gmail.com"),
                "Apache License Version 2.0",
                "https://github.com/Liferay-Porto-Digital/liferay-pd-backend/blob/main/LICENSE",
                new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}
