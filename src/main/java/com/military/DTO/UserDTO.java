package com.military.DTO;

import com.military.Entity.User.Role;

public record UserDTO(
		String email, 
		String username, 
		Role role, 
		String teamCode
) {}
