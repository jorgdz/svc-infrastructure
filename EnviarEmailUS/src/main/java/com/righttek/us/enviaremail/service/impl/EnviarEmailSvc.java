package com.righttek.us.enviaremail.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.righttek.modelocanonico.email.EmailNotificacionAtraso;
import com.righttek.modelocanonico.email.EmailSimple;
import com.righttek.us.enviaremail.service.contract.IEnviarEmailSvc;

/**
 * 
 * @author JORGE
 *
 */
@Service
public class EnviarEmailSvc implements IEnviarEmailSvc {
	
	private static final Logger LOG = LoggerFactory.getLogger(EnviarEmailSvc.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SpringTemplateEngine template;
	
	@Override
	public void enviarEmailSimple(EmailSimple emailSimple) {
		LOG.info("Inicia enviar email simple service");
		
		SimpleMailMessage email = new SimpleMailMessage();
		
		email.setTo(emailSimple.getDestinatario());
		email.setSubject(emailSimple.getAsunto());
		email.setText(emailSimple.getContenido());
		
		mailSender.send(email);
		
		LOG.info("Finaliza enviar email service");
	}

	@Override
	public void enviarNotificacionAtraso(EmailNotificacionAtraso emailSimple) throws MessagingException {
		LOG.info("INICIA ENVIAR NOTIFICACIÓN DE ATRASO SERVICE");
		
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, 
			MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, 
			StandardCharsets.UTF_8.name());
		
		helper.addAttachment("springboot.png", new ClassPathResource("/static/springboot.png"));
		
		Map<String, Object> detalles = new HashMap<>();
		
		detalles.put("nombreDestinatario", emailSimple.getDestinatario());
		detalles.put("minutosAtraso", emailSimple.getMinutosAtraso());
		detalles.put("nombreEmpleado", emailSimple.getNombreEmpleado());
		detalles.put("lugar", "GUAYAQUIL - ECUADOR");
		detalles.put("firma", "ADMINISTRADOR");
		
		Context context = new Context();
		context.setVariables(detalles);
		
		String html = template.process("template-email", context);
		
		helper.setTo(emailSimple.getDestinatario());
		helper.setSubject(emailSimple.getAsunto());
		helper.setText(html, true);
		
		mailSender.send(message);
		
		LOG.info("FINALIZA ENVIAR NOTIFICACION DE ATRASO SERVICE");
	}

}
