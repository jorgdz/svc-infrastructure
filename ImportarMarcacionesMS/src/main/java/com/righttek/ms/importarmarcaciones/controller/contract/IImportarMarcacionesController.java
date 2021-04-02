package com.righttek.ms.importarmarcaciones.controller.contract;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
public interface IImportarMarcacionesController {

	/**
	 * 
	 * @param fecha
	 * @return
	 */
	@PostMapping(value = "/v1", produces = "application/json")
	@Operation(description = "Capacidad que permite importar las marcaciones")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "400", description = "ERROR DE CLIENTE"),
		@ApiResponse(responseCode = "500", description = "ERROR INTERNO")
	})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Día al que corresponde las marcaciones a ser importadas", content = @Content(schema = @Schema(implementation = String.class)))
	ResponseEntity<?> importarMarcaciones(@RequestBody String fecha); 
}
