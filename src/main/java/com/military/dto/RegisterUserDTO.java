package com.military.dto;

import com.military.entity.User.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserDTO(
		@NotBlank(message = "Username is required")
		String username,
		
		@Email(message = "Invalid email format") @NotBlank(message = "Email is required")               
		String  email,
		
		@NotBlank(message = "Password is required")
		String password,
		
		@NotNull(message = "Role is required")
		Role role,
		
		@NotBlank(message = "Team code is required")
		String teamCode
		) {}

