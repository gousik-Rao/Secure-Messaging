package com.military.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControlTest {

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void  registerUserTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"username\":\"testuser\", "
					+ "\"password\":\"password123\", "
					+ "\"email\":\"testuser@gmail.com\","
					+ "\"role\":\"Admin\","
					+ "\"teamCode\":\"B\"}"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
