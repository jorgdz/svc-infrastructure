package com.righttek.gotalent.marcacionesexternas.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;
import com.righttek.gotalent.marcacionesexternas.repository.contact.IMarcacionesExternasRepository;

import com.righttek.gotalent.marcacionesexternas.service.contract.IMarcacionesExternasServicio;

/**
 * @author Righttek
 * @author CESAR GARCIA
 * @since 2020 abril
 */

@Service
public class MarcacionesExternasServicio implements IMarcacionesExternasServicio {

	private static final Logger LOGGER = LoggerFactory.getLogger(MarcacionesExternasServicio.class);

	@Autowired
	IMarcacionesExternasRepository repository;

	@Override
	public List<MarcacionExternaDTO> consultarMarcaciones(String fecha) throws Exception {
		// TODO Auto-generated method stub
		List<MarcacionExternaDTO> response = null;

		// try {
		LOGGER.info("INICIO DE CONSUMO DEL SERVICIO");
		/**
		 * Se realiza el llamado del metodo que consulta marcaciones externa
		 */
		response = repository.consultarMarcacionesExternas(fecha);

		// } catch (Exception e) {

		// LOGGER.error(e.getMessage());

		// }
		return response;

	}

}
