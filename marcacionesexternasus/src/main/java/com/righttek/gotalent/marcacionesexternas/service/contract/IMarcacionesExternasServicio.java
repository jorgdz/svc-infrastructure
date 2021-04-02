package com.righttek.gotalent.marcacionesexternas.service.contract;

import java.util.List;

import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;

/**
 * @author Righttek
 * @author CESAR GARCIA
 * @since 2020
 */

public interface IMarcacionesExternasServicio {

	public List<MarcacionExternaDTO> consultarMarcaciones(String fecha) throws Exception;
}
