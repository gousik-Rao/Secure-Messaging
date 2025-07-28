package com.military.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public String sendMessage(String message) {
		return message;
	}
}
