package com.military.DTO;

public record AuthResponseDTO(
		Long userId,
		String username, 
		String email,
		String role,
		String teamCode,
		String message
) {}
