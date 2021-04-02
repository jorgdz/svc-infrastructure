package com.righttek.es.marcacion.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.righttek.es.marcacion.repository.model.MarcacionExterna;
import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;

public final class MarcacionConvert {
	
	private MarcacionConvert () {}
	
	public static final MarcacionExternaDTO mapModelToDTO (MarcacionExterna marcacionExterna) {
		ModelMapper mp = new ModelMapper();
		return mp.map(marcacionExterna, MarcacionExternaDTO.class);
	}
	
	public static final MarcacionExterna mapDTOToModel (MarcacionExternaDTO marcacionExternaDto) {
		ModelMapper mp = new ModelMapper();
		return mp.map(marcacionExternaDto, MarcacionExterna.class);
	}
	
	public static final List<MarcacionExternaDTO> mapListModelToDto (List<MarcacionExterna> marcacionesExternas) {
		return marcacionesExternas.stream()
			.map(marcacionExterna -> mapModelToDTO(marcacionExterna))
			.collect(Collectors.toList());
	}
}
