package com.righttek.ms.importarmarcaciones.service.command.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.righttek.ms.importarmarcaciones.constants.ImportarMarcacionesConstants;
import com.righttek.ms.importarmarcaciones.service.command.ICommand;
import com.righttek.ms.importarmarcaciones.service.command.IParam;
import com.righttek.ms.importarmarcaciones.service.dto.MarcacionExternaParam;

/**
 * 
 * @author JORGE
 *
 */
@Component
public class CrearMarcacionesCommand implements ICommand {
	
	private static final Logger LOG = LoggerFactory.getLogger(CrearMarcacionesCommand.class);

	@Value("${uri.marcacion}")
	private String hostService;
	
	@Override
	public Object execute(IParam param) {
		LOG.info("INICIA COMANDO CREAR MARCACIONES");
		
		MarcacionExternaParam marcacion = (MarcacionExternaParam) param;
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Content-Type", "application/json");
		
		HttpEntity<MarcacionExternaParam> request = new HttpEntity<MarcacionExternaParam>(marcacion, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate
			.exchange(hostService.concat(ImportarMarcacionesConstants.CREAR_MARCACIONES), 
			HttpMethod.POST, request, String.class);
		
		LOG.info("FINALIZA COMANDO CREAR MARCACIONES");
		return response.getBody();
	}

	@Override
	public void undo() {
		
	}

}
