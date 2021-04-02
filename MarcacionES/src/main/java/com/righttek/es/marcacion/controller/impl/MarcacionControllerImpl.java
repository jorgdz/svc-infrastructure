package com.righttek.es.marcacion.controller.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.righttek.es.marcacion.constants.MarcacionConstants;
import com.righttek.es.marcacion.controller.contract.IMarcacionController;
import com.righttek.es.marcacion.event.MarcacionKafkaSender;
import com.righttek.es.marcacion.exception.custom.ConflictException;
import com.righttek.es.marcacion.exception.custom.MarcacionFechaException;
import com.righttek.es.marcacion.exception.custom.NotFoundException;
import com.righttek.es.marcacion.service.contract.IMarcacionSvc;
import com.righttek.es.marcacion.utils.MarcacionDataValidator;
import com.righttek.logicacomun.constants.SystemMessage;
import com.righttek.modelocanonico.email.EmailNotificacionAtraso;
import com.righttek.modelocanonico.email.EmailSimple;
import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author JORGE
 *
 */
@RestController
@RequestMapping("/api/es/marcacion")
@Tag(name = "MarcacionES v1", description = "Capacidades del servicio de entidad Marcacion")
public class MarcacionControllerImpl implements IMarcacionController {

	private static final Logger LOG = LoggerFactory.getLogger(MarcacionControllerImpl.class);
	
	@Autowired
	private IMarcacionSvc serviceMarcacion;
	
	@Autowired
	private MarcacionKafkaSender marcacionKafkaSender; 
	
	@Override
	public ResponseEntity<?> consultarMarcaciones(String fechaInicio, String fechaFin) {
		if (!MarcacionDataValidator.validarFecha(fechaInicio) || 
			!MarcacionDataValidator.validarFecha(fechaFin))
			throw new MarcacionFechaException(MarcacionConstants.FECHA_ERROR);
		
		LOG.info("Inicia consultar marcaciones.");
		
		List<MarcacionExternaDTO> marcacionesDTO = this.serviceMarcacion
			.consultarMarcaciones(fechaInicio, fechaFin);
		
		LOG.info("Finaliza consultar marcaciones.");
		
		if (marcacionesDTO.isEmpty()) 
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(marcacionesDTO);
	}

	@Override
	public ResponseEntity<?> consultarMarcacionesPorEmpleado(String email, String fechaInicio, String fechaFin) {
		if (!MarcacionDataValidator.validarFecha(fechaInicio) || 
			!MarcacionDataValidator.validarFecha(fechaFin))
			throw new MarcacionFechaException(MarcacionConstants.FECHA_ERROR);
			
		LOG.info("Inicia consultar marcaciones por empleado.");
		
		List<MarcacionExternaDTO> marcacionesDTO = this.serviceMarcacion
			.consultarMarcacionesPorEmpleado(email, fechaInicio, fechaFin);
			
		LOG.info("Finaliza consultar marcaciones por empleado.");
		
		if (marcacionesDTO.isEmpty()) 
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(marcacionesDTO);
	}

	@Override
	public ResponseEntity<?> crearMarcacion(MarcacionExternaDTO marcacion) {
		LOG.info("Inicia crear marcación.");
		
		MarcacionExternaDTO marcacionDTO = serviceMarcacion.crearMarcacion(marcacion);
		
		if (marcacionDTO == null)
			throw new ConflictException(SystemMessage.RECURSO_NO_CREADO);
		
		if (marcacion.getCodigoMarcacion() == 1) {
			double minutosAtraso = MarcacionDataValidator.validarAtraso(marcacion); 
			if (minutosAtraso != 0.0) {
				EmailSimple email = new EmailSimple();
				
				email.setDestinatario("yoyi_isrrael5@yahoo.com");
				email.setAsunto("Marca Atraso");
				email.setContenido("El empleado " + marcacion.getEmailEmpleado() + " a marcado con " + minutosAtraso + " minuto/s de atraso");
				
				//marcacionKafkaSender.sendMessage("EmailSimpleEvent", email);
				EmailNotificacionAtraso emailAtraso = new EmailNotificacionAtraso();
				emailAtraso.setAsunto("Marca Atraso");
				emailAtraso.setDestinatario("jdzm@outlook.es");
				emailAtraso.setNombreDestinatario("LIDER");
				emailAtraso.setNombreEmpleado(marcacionDTO.getEmailEmpleado());
				emailAtraso.setMinutosAtraso(minutosAtraso);
				
				marcacionKafkaSender.sendMessage("NotificacionAtrasoEvent", emailAtraso);
			}
		}
		
		LOG.info("Finaliza crear marcación.");
		return new ResponseEntity<String>(SystemMessage.RECURSO_CREADO, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> actualizarMarcaciones(MarcacionExternaDTO marcacion) {
		LOG.info("Inicia actualización de marcación.");
		
		if(Boolean.FALSE.equals(this.serviceMarcacion.existeIdDeMarcacion(marcacion.getIdMarcacion())))
			throw new NotFoundException("Recurso no encontrado.");
		
		MarcacionExternaDTO marcacionExterna = this.serviceMarcacion.actualizarMarcacion(marcacion);
		
		LOG.info("Finaliza actualizar marcacion.");
		
		if (marcacionExterna == null)
			throw new ConflictException("El recurso no fue actualizado.");
		
		return new ResponseEntity<String>("Recurso actualizado.", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> eliminarMarcacion(int idMarcacion) {
		LOG.info("Inicia elimina marcacion");
		
		if(Boolean.FALSE.equals(this.serviceMarcacion.existeIdDeMarcacion(idMarcacion)))
			throw new NotFoundException("Recurso no encontrado.");
		
		this.serviceMarcacion.eliminarMarcacion(idMarcacion);
		
		LOG.info("Finaliza eliminar marcación");
		
		return new ResponseEntity<String>("Recurso eliminado.", HttpStatus.OK);
	}

}
