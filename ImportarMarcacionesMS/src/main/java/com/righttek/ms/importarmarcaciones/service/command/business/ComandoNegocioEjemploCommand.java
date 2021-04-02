package com.righttek.ms.importarmarcaciones.service.command.business;

import org.springframework.stereotype.Component;

import com.righttek.ms.importarmarcaciones.service.command.ICommand;
import com.righttek.ms.importarmarcaciones.service.command.IParam;
import com.righttek.ms.importarmarcaciones.service.dto.MarcacionExternaParam;

@Component
public class ComandoNegocioEjemploCommand implements ICommand {

	@Override
	public Object execute(IParam param) {
		MarcacionExternaParam marcacion = (MarcacionExternaParam) param;
		
		String result = ""; 
		
		switch (marcacion.getCodigoMarcacion()) {
		case 1:
			result = "ENTRADA";
			break;
		case 2:
			result = "ALMUERZO";
			break;
		case 3:
			result = "FIN ALMUERZO";
			break;
		case 4:
			result = "SALIDA";
			break;
		default:
			break;
		}
		
		return result;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
