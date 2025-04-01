package com.military.DTO;

public record SendMessageDTO(
		int sender_id, int recipient_id, String messageContent) {
}
