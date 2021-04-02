package com.righttek.es.marcacion.service.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.righttek.es.marcacion.repository.contract.IMarcacionRepository;
import com.righttek.es.marcacion.repository.model.MarcacionExterna;
import com.righttek.es.marcacion.service.contract.IMarcacionSvc;
import com.righttek.es.marcacion.utils.MarcacionConvert;
import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;

/**
 * 
 * @author JORGE
 *
 */
@Service
public class MarcacionSvc implements IMarcacionSvc {
	
	private static final Logger LOG = LoggerFactory.getLogger(MarcacionSvc.class);
	
	@Autowired
	private IMarcacionRepository marcacionRepository;
	
	@Override
	public List<MarcacionExternaDTO> consultarMarcaciones(String fechaInicio, String fechaFin) {
		LOG.info("Consultar marcaciones service");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaHasta = null;
		List<MarcacionExternaDTO> marcacionesExternasDTO = null;
		
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(fechaFin));
			calendar.add(Calendar.HOUR, 24);
			fechaHasta = calendar.getTime();
			marcacionesExternasDTO = MarcacionConvert
				.mapListModelToDto(marcacionRepository.consultarMarcaciones(sdf.parse(fechaInicio), fechaHasta));
			
			LOG.info("Finaliza consulta marcaciones service");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		return marcacionesExternasDTO;
	}

	@Override
	public List<MarcacionExternaDTO> consultarMarcacionesPorEmpleado(String email, String fechaInicio,
			String fechaFin) {
		LOG.info("Consultar marcaciones por empleado service");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaHasta = null;
		List<MarcacionExternaDTO> marcacionesExternasDTO = null;
		
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(fechaFin));
			calendar.add(Calendar.HOUR, 24);
			fechaHasta = calendar.getTime();
			marcacionesExternasDTO = MarcacionConvert
				.mapListModelToDto(marcacionRepository.consultarMarcacionesPorEmpleado(email, sdf.parse(fechaInicio), fechaHasta));
			
			LOG.info("Finaliza consulta marcaciones por empleado service");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		return marcacionesExternasDTO;
	}

	@Override
	public MarcacionExternaDTO crearMarcacion(MarcacionExternaDTO marcacion) {
		MarcacionExterna marcacionExterna = MarcacionConvert.mapDTOToModel(marcacion);
		marcacionExterna.setIdMarcacion(null);
		marcacionExterna = marcacionRepository.save(marcacionExterna);
		return MarcacionConvert.mapModelToDTO(marcacionExterna);
	}

	@Override
	public MarcacionExternaDTO actualizarMarcacion(MarcacionExternaDTO marcacion) {
		MarcacionExterna marcacionExterna = MarcacionConvert.mapDTOToModel(marcacion);
		marcacionExterna = marcacionRepository.save(marcacionExterna);
		return MarcacionConvert.mapModelToDTO(marcacionExterna);
	}

	@Override
	public void eliminarMarcacion(int idMarcacion) {
		this.marcacionRepository.deleteById(BigInteger.valueOf(idMarcacion));
	}

	@Override
	public Boolean existeIdDeMarcacion(int idMarcacion) {
		return this.marcacionRepository.existsById(BigInteger.valueOf(idMarcacion));
	}

}
