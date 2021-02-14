package com.appgate.mathoperations.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.appgate.mathoperations.api.util.Utility;
import com.google.common.base.Predicates;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MathOperationsApi {

	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(MathOperationsApi.class);
		log.info("Start Checkout Api");
		SpringApplication.run(MathOperationsApi.class, args);
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(Utility.apiInfo("MathOperations"))
						.select().apis(Predicates.not(
									RequestHandlerSelectors.basePackage("org.springframework.boot")))
					.build().pathMapping("mathoperations");
	}
}
