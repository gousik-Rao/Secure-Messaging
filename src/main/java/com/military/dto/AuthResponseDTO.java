package com.military.dto;

public record AuthResponseDTO(
		Long userId,
		String username, 
		String email,
		String role,
		String teamCode,
		String message
) {}
