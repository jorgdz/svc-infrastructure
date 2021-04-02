/**
 * 
 */
package com.righttek.gotalent.marcacionesexternas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author cagc1
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class MarcacionesUsApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	void testCrearEmpleado() throws Exception {

		
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/api/us/marcacionesexternas/v1/2020-05-26")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	      		.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void contextLoads() {
	}
}
