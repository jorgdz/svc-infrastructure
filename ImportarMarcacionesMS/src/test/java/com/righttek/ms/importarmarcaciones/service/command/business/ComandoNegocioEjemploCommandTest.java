package com.righttek.ms.importarmarcaciones.service.command.business;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.righttek.ms.importarmarcaciones.service.dto.MarcacionExternaParam;

/**
 * 
 * @author JORGE
 *
 * TEst Unitario al comano de Negocio
 */
@SpringBootTest
class ComandoNegocioEjemploCommandTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	private ComandoNegocioEjemploCommand comandoEjemploCommand;
	
	private File datosJson;
	
	@BeforeEach
	void setUp() throws Exception {
		this.comandoEjemploCommand = new ComandoNegocioEjemploCommand();
		this.datosJson = new File("src/test/resources/marcacion.json");
	}

	@AfterEach
	void tearDown() throws Exception {
		this.comandoEjemploCommand = null;
		this.datosJson = null;
	}

	@Test
	void testExecuteMarcacionEntrada () throws JsonParseException, JsonMappingException, IOException {
		MarcacionExternaParam marcacion = objectMapper.readValue(datosJson, new TypeReference<List<MarcacionExternaParam>>() {}).get(0);
		
		String result = (String) comandoEjemploCommand.execute(marcacion);
		
		assertEquals("ENTRADA", result);
	}
	
	@Test
	void testExecuteMarcacionAlmuerzo () throws JsonParseException, JsonMappingException, IOException {
		MarcacionExternaParam marcacion = objectMapper.readValue(datosJson, new TypeReference<List<MarcacionExternaParam>>() {}).get(1);
		
		String result = (String) comandoEjemploCommand.execute(marcacion);
		
		assertEquals("ALMUERZO", result);
	}
	
	@Test
	void testExecuteMarcacionFinAlmuerzo () throws JsonParseException, JsonMappingException, IOException {
		MarcacionExternaParam marcacion = objectMapper.readValue(datosJson, new TypeReference<List<MarcacionExternaParam>>() {}).get(2);
		
		String result = (String) comandoEjemploCommand.execute(marcacion);
		
		assertEquals("FIN ALMUERZO", result);
	}
	
	@Test
	void testExecuteMarcacionSalida () throws JsonParseException, JsonMappingException, IOException {
		MarcacionExternaParam marcacion = objectMapper.readValue(datosJson, new TypeReference<List<MarcacionExternaParam>>() {}).get(3);
		
		String result = (String) comandoEjemploCommand.execute(marcacion);
		
		assertEquals("SALIDA", result);
	}
}
