package com.righttek.ms.importarmarcaciones.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.righttek.logicacomun.constants.SystemMessage;
import com.righttek.ms.importarmarcaciones.service.command.consumer.ConsultarMarcacionesExternasCommand;
import com.righttek.ms.importarmarcaciones.service.command.consumer.CrearMarcacionesCommand;
import com.righttek.ms.importarmarcaciones.service.contract.IImportarMarcacionesSvc;
import com.righttek.ms.importarmarcaciones.service.dto.GenericParam;
import com.righttek.ms.importarmarcaciones.service.dto.MarcacionExternaParam;

/**
 * 
 * @author JORGE
 *
 * Obtenemos la lista de marcaciones realizadas por el empleado en un fecha determinada
 * haciendo uso del comando "ConsultarMarcacionesExternasCommand"
 */
@Service
public class ImportarMarcacionesSvcImpl implements IImportarMarcacionesSvc {

	@Autowired
	private ConsultarMarcacionesExternasCommand marcacionesExternasCommand;
	
	@Autowired
	private CrearMarcacionesCommand crearMarcacionesCommand;
	
	@SuppressWarnings("unchecked")
	@Override
	public String importarMarcaciones(String fecha) {
		List<MarcacionExternaParam> marcacionesExternas = 
			(List<MarcacionExternaParam>) marcacionesExternasCommand.execute(new GenericParam(fecha));
		
		int procesados = 0;
		
		/**
		 * Por cada elemento de la lista de marcaciones externas creamos localmente las marcaciones
		 * haciendo uso del comando "CrearMarcacionesCommand"
		 */
		for (MarcacionExternaParam marcacionExternaParam : marcacionesExternas) {
			String registro = (String) crearMarcacionesCommand.execute(marcacionExternaParam);
			
			if (registro.equalsIgnoreCase(SystemMessage.RECURSO_CREADO))
				procesados ++;
		}
		
		return "SE REGISTRARON " + procesados + " NUEVAS MARCACIONES";
	}

}
