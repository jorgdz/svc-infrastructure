
package com.righttek.gotalent.marcacionesexternas.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * @author Righttek
 * @author CESAR GARCIA
 * @since 2020
 */
@Configuration
@OpenAPIDefinition
public class OpenApiConfig {
	@Bean
    public OpenAPI customOpenAPI() {
		return new OpenAPI()
                .info(new Info()
                .title("API Marcaciones Externas")
                .description("API REST de consulta de marcaciones externa").version("v0.0.1")
                .contact(new Contact().name("RIGHTTEK S.A.").email("api@righttek.com").url("https://www.righttek.com"))
                .license(new License().name("Apache 2.0").url("https://www.gnu.org/licenses/gpl-3.0.html")))
                .externalDocs(new ExternalDocumentation()
                .description("GoTalent -  MarcacionesExternasUS")
                .url("https://rgt02-224.domrgt.local/gotalent/servicios-de-utilidad/marcacionesexternasus.git"));
    }
}
