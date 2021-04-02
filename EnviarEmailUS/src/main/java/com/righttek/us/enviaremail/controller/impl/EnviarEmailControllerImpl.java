package com.righttek.us.enviaremail.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.righttek.modelocanonico.email.EmailNotificacionAtraso;
import com.righttek.modelocanonico.email.EmailSimple;
import com.righttek.us.enviaremail.controller.contract.IEnviarEmailController;
import com.righttek.us.enviaremail.service.contract.IEnviarEmailSvc;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author JORGE
 *
 */
@RestController
@RequestMapping("/api/us/enviaremail")
@Tag(name = "EnviarEmailUS v1", description = "Capacidades para el envío de Correos Electrónicos.")
public class EnviarEmailControllerImpl implements IEnviarEmailController {

	private static final Logger LOG = LoggerFactory.getLogger(EnviarEmailControllerImpl.class);
	
	@Autowired
	private IEnviarEmailSvc serviceEnviarEmail;
	
	@Override
	public ResponseEntity<String> enviarEmailSimple(EmailSimple email) {
		try {
			LOG.info("INICIA ENVIAR EMAIL SIMPLE");
			this.serviceEnviarEmail.enviarEmailSimple(email);
			LOG.info("FINALIZA ENVIAR EMAIL SIMPLE");
			
			return ResponseEntity.ok("EMAIL ENVIADO");
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<String> ("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> enviarEmailPorPlantilla(EmailNotificacionAtraso email) {
		try {
			LOG.info("INICIA ENVIAR NOTIFICACION DE ATRASO");
			this.serviceEnviarEmail.enviarNotificacionAtraso(email);
			LOG.info("FINALIZA ENVIAR NOTIFICACIÓN DE ATRASO");
			
			return ResponseEntity.ok("EMAIL ENVIADO");
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<String> ("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
