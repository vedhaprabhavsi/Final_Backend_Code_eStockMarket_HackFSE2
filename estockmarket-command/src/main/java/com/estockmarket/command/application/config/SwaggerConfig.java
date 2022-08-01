package com.estockmarket.command.application.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket commandApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.estockmarket.command.application"))
				.paths(regex("/command/market.*")).build().apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"Estock Market Command", 
				"Estock Market Command", 
				"1.0", 
				"Terms of Service", 
				new Contact("testuser", "TestUser", "testuser@gmail.com"), 
				"Apache License Version 2.0", 
				"https://www.apache.org/license.html", new ArrayList<>());
		return apiInfo;
	}
}
