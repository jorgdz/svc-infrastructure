package com.righttek.ms.importarmarcaciones.service.command.consumer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.righttek.ms.importarmarcaciones.constants.ImportarMarcacionesConstants;
import com.righttek.ms.importarmarcaciones.service.command.ICommand;
import com.righttek.ms.importarmarcaciones.service.command.IParam;
import com.righttek.ms.importarmarcaciones.service.dto.GenericParam;
import com.righttek.ms.importarmarcaciones.service.dto.MarcacionExternaParam;

/**
 * 
 * @author JORGE
 *
 */
@Component
public class ConsultarMarcacionesExternasCommand implements ICommand {

	@Value("${uri.marcaciones-externas}")
	private String hostService;
	
	private static final Logger LOG = LoggerFactory.getLogger(ConsultarMarcacionesExternasCommand.class);
	
	@Override
	public Object execute(IParam param) {
		GenericParam fecha = (GenericParam) param;
		LOG.info("INICIA COMANDO CONSULTAR MARCACIONES");
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<MarcacionExternaParam>> response = restTemplate
			.exchange(hostService.concat(ImportarMarcacionesConstants.CONSULTAR_MAR_EXTERNAS), 
			HttpMethod.GET, null, new ParameterizedTypeReference<List<MarcacionExternaParam>>() {}, fecha.getValue());
		
		LOG.info("Finaliza Comando consultar Marcaciones");
		return response.getBody();
	}

	@Override
	public void undo() {
		
	}

}
