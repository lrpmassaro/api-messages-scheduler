package com.api.messagescheduler.config;

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

@Configuration
@EnableSwagger2
public class Swagger2 {
	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.api.messagescheduler"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Message Scheduling API")
				.description("API for scheduling SMS, e-mail, push and WhatsApp messages.")
				.version("1.0")
				.license("GNU General Public License v3.0")
				.licenseUrl("https://github.com/lrpmassaro/api-messages-scheduler/blob/master/LICENSE")
				.contact(new Contact("Lucas Massaro", "https://github.com/lrpmassaro", "lucasmassaro@globomail.com"))
				.build();
	}

}
