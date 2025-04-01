package com.military.DTO;

import java.time.LocalDateTime;

import com.military.Entity.Message.Status;

public record SearchRequestDTO(
		int userId,
		String keyword,
		LocalDateTime startDate,
		LocalDateTime endDate,
		Status readStatus
		) {
}
