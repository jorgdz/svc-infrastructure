package com.righttek.es.marcacion.event;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.righttek.modelocanonico.email.EmailNotificacionAtraso;
import com.righttek.modelocanonico.email.EmailSimple;

/**
 * 
 * @author JORGE
 *
 */
@Configuration
public class ProducerConfiguration {

	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaServer;
	
	@Bean
	public Map<String, Object> producerConfigurations () {
		Map<String, Object> configuration = new HashMap<>();
		
		configuration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
		configuration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return configuration;
	}
	
	/**
	 * Email Simple
	 * @return
	 */
	@Bean
	public ProducerFactory<String, EmailSimple> producerFactoryEmailSimpleAtraso () {
		return new DefaultKafkaProducerFactory<>(producerConfigurations());
	}
	
	@Bean
	public KafkaTemplate<String, EmailSimple> kafkaTemplateEmailSimpleAtraso () {
		return new KafkaTemplate<>(producerFactoryEmailSimpleAtraso());
	}
		
	/**
	 * Email Notificacion de atraso
	 * @return
	 */
	@Bean
	public KafkaTemplate<String, EmailNotificacionAtraso> kafkaTemplateEmailNotificacionAtraso () {
		return new KafkaTemplate<>(producerFactoryEmailNotificacionAtraso ());
	}
	
	@Bean
	public ProducerFactory<String, EmailNotificacionAtraso> producerFactoryEmailNotificacionAtraso () {
		return new DefaultKafkaProducerFactory<>(producerConfigurations());
	}
}
