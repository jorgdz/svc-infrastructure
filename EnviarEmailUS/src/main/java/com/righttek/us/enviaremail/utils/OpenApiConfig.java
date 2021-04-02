package com.righttek.us.enviaremail.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * 
 * @author JORGE
 *
 */
@Configuration
@OpenAPIDefinition
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("EnviarEmailUS")
				.description("Servicio de utilidad para envío de Emails")
				.version("0.0.1")
				.contact(new Contact()
					.name("RIGHTTEK S.A.")
					.email("api@righttek.com")
					.url("https://www.righttek.com"))
				.license(new License().name("Apache 2.0").url("https://www.gnu.org/licenses/gpl-3.0.html")))
			.externalDocs(new ExternalDocumentation().description("API RESTFul EnviarEmailUS").url(""));
	}
}
