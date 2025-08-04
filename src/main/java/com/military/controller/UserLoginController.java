package com.military.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.military.dto.AuthResponseDTO;
import com.military.dto.LoginReqDTO;
import com.military.exceptions.InvalidCredentialsException;
import com.military.security.JwtUtils;
import com.military.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController @RequestMapping("/api/auth")
public class UserLoginController {

	@Autowired @Lazy
	private UserService userService;
	
	@Autowired @Lazy
	private JwtUtils jwtUtil;
	
	@PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginReqDTO loginRequest) {                  

        AuthResponseDTO userDTO = userService.validatingUser(loginRequest.email(), loginRequest.password());
       	System.out.println("Resolved userDTO: " + userDTO);

        if (userDTO == null || userDTO.username() == null)
                throw new InvalidCredentialsException("Invalid username or password");

       	String token = jwtUtil.generateToken(userDTO.username());
       	
        return ResponseEntity.ok(Map.of("token", token, "user", userDTO.username()));
        	
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
	}
}


