package com.righttek.es.marcacion.controller.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.righttek.es.marcacion.repository.contract.IMarcacionRepository;
import com.righttek.es.marcacion.repository.model.MarcacionExterna;
import com.righttek.es.marcacion.utils.MarcacionConvert;
import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;

/**
 * 
 * @author JORGE
 *
 * TEst de integración
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MarcacionControllerImplTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private IMarcacionRepository marcacionRepository;
	
	private File datosJSON;
	
	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		this.datosJSON = new File("src/test/resources/marcacion_externa.json");
	}

	@AfterEach
	void tearDown() throws Exception {
		this.marcacionRepository.deleteAll();
		this.datosJSON = null;
		this.mockMvc = null;
	}

	@Test
	void testConsultarMarcaciones() throws Exception {
		List<MarcacionExterna> marcaciones = objectMapper.readValue(datosJSON, new TypeReference<List<MarcacionExterna>>() {});
		
		this.marcacionRepository.saveAll(marcaciones);
		
		String fecha = "2021-03-30";
		
		this.mockMvc.perform(get("/api/es/marcacion/v1/{fechaInicio}/{fechaFin}", fecha, fecha)
			.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	void testConsultarMarcacionesPorEmpleado() throws Exception {
		List<MarcacionExterna> marcaciones = objectMapper.readValue(datosJSON, new TypeReference<List<MarcacionExterna>>() {});
		
		this.marcacionRepository.saveAll(marcaciones);
		
		String fecha = "2021-03-30";
		String email = "fernando@righttek.com";
		
		this.mockMvc.perform(get("/api/es/marcacion/v1/{email}/{fechaInicio}/{fechaFin}", email, fecha, fecha)
			.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	void testConsultarMarcacionesPorEmpleadoSinResultados() throws Exception {
		List<MarcacionExterna> marcaciones = objectMapper
			.readValue(datosJSON, new TypeReference<List<MarcacionExterna>>() {});
		
		this.marcacionRepository.saveAll(marcaciones);
		
		String fecha = "2021-03-30";
		String email = "fernando2@righttek.com";
		
		this.mockMvc.perform(get("/api/es/marcacion/v1/{email}/{fechaInicio}/{fechaFin}", email, fecha, fecha).accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isNoContent());
	}
	
	@Test
	void testCrearMarcacion() throws Exception {
		MarcacionExternaDTO marcacionExternaDTO = objectMapper
			.readValue(datosJSON, new TypeReference<List<MarcacionExternaDTO>>() {}).get(0);
		
		this.mockMvc.perform(post("/api/es/marcacion/v1").content(objectMapper.writeValueAsString(marcacionExternaDTO)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isCreated());
	}

	@Test
	void testActualizarMarcaciones() throws Exception {
		MarcacionExterna marcacionExterna = objectMapper
			.readValue(datosJSON, new TypeReference<List<MarcacionExterna>>() {}).get(2);
		
		MarcacionExternaDTO marcacionExternaDTO = MarcacionConvert.mapModelToDTO(this.marcacionRepository.save(marcacionExterna));
		
		marcacionExternaDTO.setEmailEmpleado("fernandolavado@righttek.com");
		marcacionExternaDTO.setIdOrigenMarcacion(12);
		
		this.mockMvc.perform(put("/api/es/marcacion/v1").content(objectMapper.writeValueAsString(marcacionExternaDTO)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("Recurso actualizado."));
	}

	@Test
	void testActualizarMarcacionNoEncontrada() throws Exception {
		MarcacionExterna marcacionExterna = objectMapper
			.readValue(datosJSON, new TypeReference<List<MarcacionExterna>>() {}).get(2);
		
		MarcacionExternaDTO marcacionExternaDTO = MarcacionConvert.mapModelToDTO(this.marcacionRepository.save(marcacionExterna));
		
		marcacionExternaDTO.setIdMarcacion(0);
		
		this.mockMvc.perform(put("/api/es/marcacion/v1").content(objectMapper.writeValueAsString(marcacionExternaDTO)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.message", is("Recurso no encontrado.")));
	}
	
	@Test
	void testEliminarMarcacion() throws Exception {
		MarcacionExterna marcacion = objectMapper
			.readValue(datosJSON, new TypeReference<List<MarcacionExterna>>() {}).get(0);
		
		marcacion = this.marcacionRepository.save(marcacion);
	
		this.mockMvc.perform(delete("/api/es/marcacion/v1/{idMarcacion}", marcacion.getIdMarcacion()))
			.andDo(print())
	      	.andExpect(status().isOk())
	      	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      	.andExpect(content().string("Recurso eliminado."));
	}

	@Test
	void testEliminarMarcacionNoEncontrado () throws Exception {
		this.mockMvc.perform(delete("/api/es/marcacion/v1/{idMarcacion}", 2))
			.andDo(print())
	      	.andExpect(status().isNotFound());
	}

}
