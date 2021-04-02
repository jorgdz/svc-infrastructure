package com.righttek.gotalent.marcacionesexternas.repository.contact;


import java.util.List;

import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;

/**
 * @author Righttek
 * @author CESAR GARCIA
 * @since 2020 Mayo
 */

public interface IMarcacionesExternasRepository {

	public List<MarcacionExternaDTO> consultarMarcacionesExternas(String fecha)throws Exception ;
}
