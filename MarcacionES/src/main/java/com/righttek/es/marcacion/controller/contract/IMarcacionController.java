package com.righttek.es.marcacion.controller.contract;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * 
 * @author JORGE
 *
 */
@Validated
public interface IMarcacionController {

	/**
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return Listado de marcaciones por rango de fechas
	 */
	@GetMapping(value = "/v1/{fechaInicio}/{fechaFin}", headers="Accept=application/json")
	@Operation(description = "Listar Marcaciones según un rango de fecha")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MarcacionExternaDTO.class)))),
		@ApiResponse(responseCode = "204", description = "NO HAY CONTENIDO"),
		@ApiResponse(responseCode = "500", description = "ERROR INTERNO", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class))))
	})
	@Parameters(value = {
		@Parameter(name = "fechaInicio", description = "Dia desde el que se consultarán las marcaciones", example = "2021-03-29"),
		@Parameter(name = "fechaFin", description = "Dia hasta el que se consultarán las marcaciones", example = "2021-03-29")
	})
	ResponseEntity<?> consultarMarcaciones(
		@PathVariable(name = "fechaInicio") String fechaInicio, 
		@PathVariable(name = "fechaFin") String fechaFin);
	
	
	/**
	 * 
	 * @param email
	 * @param fechaInicio
	 * @param fechaFin
	 * @return Listado de marcaciones de un empleado corporativo por rango de fechas
	 */
	@GetMapping(value = "/v1/{email}/{fechaInicio}/{fechaFin}", headers="Accept=application/json")
	@Operation(description = "Listar Marcaciones según un rango de fecha y el email del empleado")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MarcacionExternaDTO.class)))),
		@ApiResponse(responseCode = "204", description = "NO HAY CONTENIDO"),
		@ApiResponse(responseCode = "500", description = "ERROR INTERNO", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class))))
	})
	@Parameters(value = {
		@Parameter(name = "email", description = "Email corporativo del empleado", example = "example@righttek.com"),
		@Parameter(name = "fechaInicio", description = "Dia desde el que se consultarán las marcaciones", example = "2021-03-29"),
		@Parameter(name = "fechaFin", description = "Dia hasta el que se consultarán las marcaciones", example = "2021-03-29")
	})
	ResponseEntity<?> consultarMarcacionesPorEmpleado(
		@PathVariable(name = "email") @Valid @Email(message = "Correo no válido.") String email, 
		@PathVariable(name = "fechaInicio") String fechaInicio, 
		@PathVariable(name = "fechaFin") String fechaFin);
	
	
	/**
	 * 
	 * @param marcacion
	 * @return nueva marcacion
	 */
	@PostMapping(value = "/v1", headers="Accept=application/json")
	@Operation(description = "Crear una marcación externa")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "CREADO", content = @Content(schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "500", description = "ERROR INTERNO", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class))))
	})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Estructura de datos de una marcación externa", content = @Content(schema = @Schema(implementation = MarcacionExternaDTO.class)))
	ResponseEntity<?> crearMarcacion(@RequestBody @Valid MarcacionExternaDTO marcacion);
	
	
	/**
	 * 
	 * @param marcacion
	 * @return marcacion actualizada
	 */
	@PutMapping(value = "/v1", headers="Accept=application/json")
	@Operation(description = "Actualizar una marcación externa")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "500", description = "ERROR INTERNO", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class))))
	})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Estructura de datos de una marcación externa", content = @Content(schema = @Schema(implementation = MarcacionExternaDTO.class)))
	ResponseEntity<?> actualizarMarcaciones(@RequestBody MarcacionExternaDTO marcacion);
	
	
	/**
	 * 
	 * @param idMarcacion
	 * @return
	 */
	@DeleteMapping(value = "/v1/{idMarcacion}", headers="Accept=application/json")
	@Operation(description = "Eliminar una marcación externa")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "500", description = "ERROR INTERNO", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class))))
	})
	@Parameter(name = "idMarcacion", description = "Id de marcación", example = "1")
	ResponseEntity<?> eliminarMarcacion(@PathVariable(name = "idMarcacion") @Positive int idMarcacion);
}
