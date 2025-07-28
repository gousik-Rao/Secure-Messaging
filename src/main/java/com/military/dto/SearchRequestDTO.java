package com.military.dto;

import java.time.LocalDateTime;

import com.military.entity.Message.Status;

public record SearchRequestDTO(
		int userId,
		String keyword,
		LocalDateTime startDate,
		LocalDateTime endDate,
		Status readStatus
		) {
}
