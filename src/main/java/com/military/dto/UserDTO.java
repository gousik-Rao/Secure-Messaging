package com.military.dto;

import com.military.entity.User.Role;

public record UserDTO(
		String email, 
		String username, 
		Role role, 
		String teamCode
) {}
