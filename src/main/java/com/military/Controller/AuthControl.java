package com.military.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.military.DTO.AuthResponseDTO;
import com.military.DTO.LoginReqDTO;
import com.military.Exceptions.InvalidCredentialsException;
import com.military.Security.JWTUtil;
import com.military.Service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController @RequestMapping("/api/auth")
public class AuthControl {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginReqDTO loginRequest, BindingResult result) {                  

        if (result.hasErrors())
        	throw new IllegalArgumentException(result.getFieldError().getDefaultMessage());

        AuthResponseDTO userDTO = userService.validatingUser(loginRequest.email(), loginRequest.password());
        if (userDTO != null) {
        	String token = JWTUtil.generateToken(userDTO.username());
        	return ResponseEntity.ok(Map.of("token", token, "user", userDTO.username()));
        } else
        	throw new InvalidCredentialsException("Invalid username or password");
    }
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
	}
}










