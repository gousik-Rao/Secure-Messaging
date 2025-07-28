package com.military.dto;

import java.time.LocalDateTime;

public record InboxMessageDTO(
		int messageId, int senderId, String encryptedMessage,
		String status, LocalDateTime timestamp) {

}
