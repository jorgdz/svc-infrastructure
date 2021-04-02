package com.righttek.gotalent.marcacionesexternas.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.righttek.gotalent.marcacionesexternas.constants.URLsMarcacionExternas;
import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;
import com.righttek.gotalent.marcacionesexternas.repository.contact.IMarcacionesExternasRepository;
import com.righttek.gotalent.marcacionesexternas.utils.convert.MarcacionConvert;
import com.righttek.gotalent.marcacionesexternas.utils.convert.ResponseMarcacion;

/**
 * @author Righttek
 * @author CESAR GARCIA
 * @since 2020 Mayo
 */

@Service
public class MarcacionesExternasRepository implements IMarcacionesExternasRepository {

	// se declara constante
	private static final Logger LOGGER = LoggerFactory.getLogger(MarcacionesExternasRepository.class);

	@Value("${servidor}")
	private String path;

	@Override
	public List<MarcacionExternaDTO> consultarMarcacionesExternas(String fecha) throws Exception{
		// TODO Auto-generated method stub

		ResponseEntity<List<ResponseMarcacion>> response = null;
		RestTemplate restTemplate = new RestTemplate();

		List<MarcacionExternaDTO> lista = null;
		String urlCompleta = path + URLsMarcacionExternas.URL_Marcaciones;
		//try {
			/**
			 * Se consume el servicio de marcacion externas
			 */
			response = restTemplate.exchange(urlCompleta, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ResponseMarcacion>>() {
					}, fecha);

			if (response.getStatusCodeValue() == 200) {

				LOGGER.info("exito code:  " + response.getStatusCodeValue());

				List<ResponseMarcacion> guardar = (List<ResponseMarcacion>) response.getBody();

				if (!guardar.isEmpty()) {
					/**
					 * Se asigna los valores al DTO
					 */
					lista = MarcacionConvert.convertModelADTO(guardar);

					return lista;
				}

			}
		/*} catch (Exception ex) {
			LOGGER.error(ex.getMessage());

		}*/

		return lista;

	}

}
