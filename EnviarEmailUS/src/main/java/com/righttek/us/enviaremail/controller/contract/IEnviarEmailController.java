package com.righttek.us.enviaremail.controller.contract;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.righttek.modelocanonico.email.EmailNotificacionAtraso;
import com.righttek.modelocanonico.email.EmailSimple;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * 
 * @author JORGE
 *
 */
public interface IEnviarEmailController {
	
	/**
	 * Enviar email simple
	 * @param email
	 * @return
	 */
	@PostMapping(value = "/v1", produces = "application/json", consumes = "application/json")
	@Operation(description = "Capacidad para realizar el envío de un email simple.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "EMAIL ENVIAR", content = @Content(schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "400", description = "ERROR DE CLIENTE", content = @Content(schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "500", description = "ERROR INTERNO", content = @Content(schema = @Schema(implementation = String.class)))
	})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "MODELO DE DATO DE UN EMAIL SIMPLE", content = @Content(schema = @Schema(implementation = EmailSimple.class)))
	ResponseEntity<String> enviarEmailSimple (@RequestBody EmailSimple email);
	
	/**
	 * Enviar notificación de atraso
	 * @param email
	 * @return
	 */
	@PostMapping(value = "/atraso/v1", produces = "application/json", consumes = "application/json")
	@Operation(description = "Capacidad para realizar el envío de una notificación de atraso.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "EMAIL ENVIAR", content = @Content(schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "400", description = "ERROR DE CLIENTE", content = @Content(schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "500", description = "ERROR INTERNO", content = @Content(schema = @Schema(implementation = String.class)))
	})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "MODELO DE DATO DE UNA NOTIFICACIÓN DE ATRASO", content = @Content(schema = @Schema(implementation = EmailSimple.class)))
	ResponseEntity<String> enviarEmailPorPlantilla (@RequestBody EmailNotificacionAtraso email);
}
