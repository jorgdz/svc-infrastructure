package com.righttek.us.enviaremail.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.righttek.modelocanonico.email.EmailNotificacionAtraso;
import com.righttek.modelocanonico.email.EmailSimple;
import com.righttek.us.enviaremail.service.contract.IEnviarEmailSvc;

/**
 * 
 * @author JORGE
 *
 */
@Component
public class TopicConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(TopicConsumer.class);
	
	@Autowired
	IEnviarEmailSvc serviceEnviarEmail;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@KafkaListener(topics = "EmailSimpleEvent", groupId = "myGroup")
	public void listenEmailSimple (@Payload String message, @Headers MessageHeaders headers) {
		LOG.info("Datos recibidos={}", message);
		
		headers.keySet().forEach(key -> LOG.info("{}: {}", key, headers.get(key)));
		
		try {
			EmailSimple msg = objectMapper.readValue(message, EmailSimple.class);
			this.serviceEnviarEmail.enviarEmailSimple(msg);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}
	
	@KafkaListener(topics = "NotificacionAtrasoEvent", groupId = "myGroup")
	public void listenNotificacionAtraso (@Payload String message, @Headers MessageHeaders headers) {
		LOG.info("Datos recibidos={}", message);
		
		headers.keySet().forEach(key -> LOG.info("{}: {}", key, headers.get(key)));
		
		try {
			EmailNotificacionAtraso msg = objectMapper.readValue(message, EmailNotificacionAtraso.class);
			this.serviceEnviarEmail.enviarNotificacionAtraso(msg);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}
	
}
