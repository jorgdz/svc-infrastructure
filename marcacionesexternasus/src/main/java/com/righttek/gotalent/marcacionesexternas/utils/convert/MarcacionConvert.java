/**
 * 
 */
package com.righttek.gotalent.marcacionesexternas.utils.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;

/**
 * @author Righttek
 * @author CESAR GARCIA
 * @since 2020
 */

public class MarcacionConvert {

	public MarcacionConvert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static List<MarcacionExternaDTO> convertModelADTO(List<ResponseMarcacion> marcacion) {

		List<MarcacionExternaDTO> lista = new ArrayList<>();
		MarcacionExternaDTO datos;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (ResponseMarcacion item : marcacion) {
			datos = new MarcacionExternaDTO();
			datos.setEmailEmpleado(item.getLength());
			try {
				datos.setFechaHora(formatter.parse(item.getDateMarked()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			datos.setCodigoMarcacion(item.getTypeMarked());
	
			datos.setIdOrigenMarcacion(item.getIdMarking());
			lista.add(datos);

		}

		return lista;
	}
}
