
package com.righttek.gotalent.marcacionesexternas.controller.contract;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author Righttek
 * @author CESAR GARCIA
 * @since 2020
 */

public interface IMarcacionesExternasController {

	@GetMapping(value = "/v1/{fecha}")
	@Operation(description = "Obtener la lista total de empleados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MarcacionExternaDTO.class))),
			@ApiResponse(responseCode = "204", description = "NO ENCONTRADO") })
	public ResponseEntity<?> ConsultarMarcaciones(
			@Parameter(name = "Fecha a consultar", required = true) @PathVariable(value = "fecha", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String fecha);

}
