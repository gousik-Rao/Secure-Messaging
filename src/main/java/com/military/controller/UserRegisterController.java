package com.military.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.military.dto.RegisterUserDTO;
import com.military.entity.User;
import com.military.service.UserService;

import jakarta.validation.Valid;


@RestController @RequestMapping("/api/auth") 
public class UserRegisterController {

	@Autowired
	private UserService userService;
    
//    User Registration
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterUserDTO registerUserDTO, BindingResult result) {        
//    	Handle validation errors
    	if(result.hasErrors()) {
    		throw new IllegalArgumentException(result.getFieldError().getDefaultMessage());
    	}
    	User user = new User();
    	user.setEmail(registerUserDTO.email());
    	user.setPassword(registerUserDTO.password());
    	user.setUsername(registerUserDTO.username());
    	user.setRole(registerUserDTO.role());
    	user.setTeamCode(registerUserDTO.teamCode());
    	
    	userService.registerUser(user);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }
}



















