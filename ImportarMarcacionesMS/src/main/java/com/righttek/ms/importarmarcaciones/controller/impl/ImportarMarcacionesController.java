package com.righttek.ms.importarmarcaciones.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.righttek.logicacomun.constants.SystemMessage;
import com.righttek.logicacomun.utilitarios.DataValidator;
import com.righttek.ms.importarmarcaciones.controller.contract.IImportarMarcacionesController;
import com.righttek.ms.importarmarcaciones.exception.custom.MarcacionFechaException;
import com.righttek.ms.importarmarcaciones.service.contract.IImportarMarcacionesSvc;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/ms/importarmarcaciones")
@Tag(name = "ImportarMarcaciones v0.0.1", description = "Capacidades del microservicio que permite importar las marcaciones")
public class ImportarMarcacionesController implements IImportarMarcacionesController {

	private static final Logger LOG = LoggerFactory.getLogger(ImportarMarcacionesController.class);
	
	@Autowired
	private IImportarMarcacionesSvc importarMarcacionesSvc;
	
	@Override
	public ResponseEntity<?> importarMarcaciones(String fecha) {
		if (!DataValidator.validarFecha(fecha))
			throw new MarcacionFechaException(SystemMessage.FECHA_ERROR);
		
		LOG.info("INICIA IMPORTAR MARCACIONES");
		
		String result = importarMarcacionesSvc.importarMarcaciones(fecha);
		
		LOG.info("FINALIZAR IMPORTAR MARCACIONES");
		return ResponseEntity.ok(result);
	}

}
