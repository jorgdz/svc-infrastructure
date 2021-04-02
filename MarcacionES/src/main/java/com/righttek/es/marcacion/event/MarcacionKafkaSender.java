package com.righttek.es.marcacion.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.righttek.modelocanonico.email.EmailNotificacionAtraso;
import com.righttek.modelocanonico.email.EmailSimple;

/**
 * 
 * @author JORGE
 *
 */
@Component
public class MarcacionKafkaSender {

	@Autowired
	private KafkaTemplate<String, EmailSimple> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, EmailNotificacionAtraso> kafkaTemplateNotificacion;
	
	private static final Logger LOG = LoggerFactory.getLogger(MarcacionKafkaSender.class);
	
	@Value("${message.topic.name:test}")
	private String topicName;
	
	public void sendMessage (String topic, EmailSimple body) {
		if (topic.isEmpty() || topic == null || topic.trim().equals("")) {
			topic = topicName;
		}
		
		Message<EmailSimple> message = MessageBuilder.withPayload(body)
			.setHeader(KafkaHeaders.TOPIC, topic).build();
		
		ListenableFuture<SendResult<String, EmailSimple>> future = kafkaTemplate.send(message);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, EmailSimple>>() {

			@Override
			public void onSuccess(SendResult<String, EmailSimple> result) {
				LOG.info("Mensaje enviado = [{}] con desplazamiento = [{}]", message, result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				LOG.warn("No se puede enviar mensaje = [{}] debido a: {}", message, ex.getMessage());
			}
		
		});
	}
	
	public void sendMessage (String topic, EmailNotificacionAtraso body) {
		if (topic.isEmpty() || topic == null || topic.trim().equals("")) {
			topic = topicName;
		}
		
		Message<EmailNotificacionAtraso> message = MessageBuilder.withPayload(body)
			.setHeader(KafkaHeaders.TOPIC, topic).build();
		
		ListenableFuture<SendResult<String, EmailNotificacionAtraso>> future = kafkaTemplateNotificacion.send(message);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, EmailNotificacionAtraso>>() {

			@Override
			public void onSuccess(SendResult<String, EmailNotificacionAtraso> result) {
				LOG.info("Mensaje enviado = [{}] con desplazamiento = [{}]", message, result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				LOG.warn("No se puede enviar mensaje = [{}] debido a: {}", message, ex.getMessage());
			}
		
		});
	}
}
