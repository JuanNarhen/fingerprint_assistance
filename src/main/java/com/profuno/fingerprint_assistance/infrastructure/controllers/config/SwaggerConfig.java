package com.profuno.fingerprint_assistance.infrastructure.controllers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Fingerprint repository controllers")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.profuno.fingerprint_assistance.infrastructure.controllers"))
                .paths(PathSelectors.any()) .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Manage Fingerprint Entities Controllers",
                "Service Rest API Crud for fingerprint system repositories",
                "1.1",
                "Terms of service",
                new Contact("Juan Naranjo", "Contact URL", "jcnaranjo79678@umanizales.edu.co"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList() ); }
}
