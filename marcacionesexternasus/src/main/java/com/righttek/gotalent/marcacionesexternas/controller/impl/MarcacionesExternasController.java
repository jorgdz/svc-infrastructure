package com.righttek.gotalent.marcacionesexternas.controller.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.righttek.gotalent.marcacionesexternas.controller.contract.IMarcacionesExternasController;
import com.righttek.gotalent.marcacionesexternas.controller.dto.MensajeSistema;
import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;
import com.righttek.gotalent.marcacionesexternas.repository.impl.MarcacionesExternasRepository;
import com.righttek.gotalent.marcacionesexternas.service.impl.MarcacionesExternasServicio;
import com.righttek.logicacomun.utilitarios.DataValidator;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Righttek
 * @author CESAR GARCIA
 * @since 2020
 */

@RestController
@RequestMapping("/api/us/marcacionesexternas")
@Tag(name = "MarcacionExternasUS v1.0", description = "Servicio Utilida de Marcacion Externas")
public class MarcacionesExternasController implements IMarcacionesExternasController {

	@Autowired
	MarcacionesExternasServicio servicio;

	// se declara constante
	private static final Logger LOGGER = LoggerFactory.getLogger(MarcacionesExternasRepository.class);

	@Override
	public ResponseEntity<?> ConsultarMarcaciones(String fecha) {
		// TODO Auto-generated method stub
		List<MarcacionExternaDTO> response = null;

		try {

			/**
			 * SE VALIDAD LA FECHA
			 */

			if (DataValidator.validarFecha(fecha)) {

				response = servicio.consultarMarcaciones(fecha);

				if (response != null && !response.isEmpty()) {
					LOGGER.info("PROCESO TERMINA EXITOSO");
					return new ResponseEntity<List<MarcacionExternaDTO>>(response, HttpStatus.OK);

				} else {
					MensajeSistema error = new MensajeSistema("No contiene data", "");
					return new ResponseEntity<MensajeSistema>(error, HttpStatus.BAD_REQUEST);
				}

			} else {
				MensajeSistema error = new MensajeSistema("fecha invalidad ", "");
				return new ResponseEntity<MensajeSistema>(error, HttpStatus.BAD_REQUEST);
			}

		} catch (HttpClientErrorException e) {
			LOGGER.error("ERROR EN EL SERVICIO ");
			MensajeSistema error = new MensajeSistema(e.getResponseBodyAsString(), e.getRawStatusCode() + "");
			return new ResponseEntity<MensajeSistema>(error, HttpStatus.valueOf(e.getRawStatusCode()));
		} catch (Exception e) {
			LOGGER.error("ERROR:  " + e.getMessage());
			MensajeSistema error = new MensajeSistema(e.getMessage(), "");
			return new ResponseEntity<MensajeSistema>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
