package com.righttek.es.marcacion.service.contract;

import java.util.List;

import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;

/**
 * 
 * @author JORGE
 *
 */
public interface IMarcacionSvc {
	
	List<MarcacionExternaDTO> consultarMarcaciones (String fechaInicio, String fechaFin);
	
	List<MarcacionExternaDTO> consultarMarcacionesPorEmpleado(String email, String fechaInicio, String fechaFin);
	
	MarcacionExternaDTO crearMarcacion(MarcacionExternaDTO marcacion);
	
	MarcacionExternaDTO actualizarMarcacion(MarcacionExternaDTO marcacion);
	
	void eliminarMarcacion (int idMarcacion);
	
	Boolean existeIdDeMarcacion (int idMarcacion);
}
