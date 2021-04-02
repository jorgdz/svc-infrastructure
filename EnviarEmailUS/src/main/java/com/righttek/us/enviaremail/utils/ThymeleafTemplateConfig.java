package com.righttek.us.enviaremail.utils;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * 
 * @author JORGE
 *
 */
@Configuration
public class ThymeleafTemplateConfig {

	@Bean
	public SpringTemplateEngine springTemplateEngine () {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(templateResolver());
		return templateEngine;
	}
	
	@Bean
	@Description("Thymeleaf template resolver servering HTML 5")
	public ClassLoaderTemplateResolver templateResolver() {
		var templateResolver = new ClassLoaderTemplateResolver();
		
		templateResolver.setPrefix("templates/");
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
		
		return templateResolver;
	}
}
