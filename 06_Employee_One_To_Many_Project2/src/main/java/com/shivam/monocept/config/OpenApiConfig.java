package com.shivam.monocept.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {

		final String securitySchemeName = "basicAuth";

		return new OpenAPI()
				.info(new Info().title("Department Employee One-to-Many API").version("1.0")
						.description("Spring Boot REST API for Department and Employee Management"))
				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.schemaRequirement(securitySchemeName,
						new SecurityScheme().name(securitySchemeName).type(SecurityScheme.Type.HTTP).scheme("basic"));
	}
}