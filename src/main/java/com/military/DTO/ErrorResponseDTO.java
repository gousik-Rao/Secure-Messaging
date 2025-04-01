package com.military.DTO;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
		LocalDateTime timestamp,
		int status,
		String error,
		String message
){}
