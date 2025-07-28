package com.military.Controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.military.dto.AuthResponseDTO;
import com.military.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserLoginControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@SuppressWarnings("removal")
	@MockBean // Mock service
	private UserService userService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void  loginUserTest() throws Exception {
		// Mock DTO
		AuthResponseDTO mockUser = new AuthResponseDTO(
				4L, "testuser", "testuser@gmail.com", "Admin", "B", "Test Message");
		
		when(userService.validatingUser("testuser@gmail.com", "password123")).thenReturn(mockUser); 
		
		String json = "{\"email\":\"testuser@gmail.com\", \"password\":\"password123\"}";         
		mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
			    .content(json))
			.andExpect(MockMvcResultMatchers.status().isUnauthorized())
			.andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
			
		.andExpect(MockMvcResultMatchers.jsonPath("$.user").value("testuser"));
	}
	
	@Test
	void loginUserInvalidTest() throws Exception {
	    when(userService.validatingUser("invalid@gmail.com", "wrongpass")).thenReturn(null);
	    
	    String json = "{\"email\":\"invalid@gmail.com\", \"password\":\"wrongpass\"}";
	    mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(json))
	        .andExpect(MockMvcResultMatchers.status().isUnauthorized())  // Assuming your handler returns 400         
	        .andExpect(MockMvcResultMatchers.jsonPath("$.error").exists());
	}

}
