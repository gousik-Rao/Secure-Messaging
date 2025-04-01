package com.military.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// DTO Class for login request
public record LoginReqDTO (
	
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	String email,
	
	@NotBlank(message = "Password is required")
	String password
	){}

