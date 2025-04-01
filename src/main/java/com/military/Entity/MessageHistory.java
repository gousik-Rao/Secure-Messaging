package com.military.Entity;

import java.time.LocalDateTime;

import com.military.Entity.Message.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity @Table(name = "message_history")
public class MessageHistory {
	
	@ManyToOne
	@JoinColumn(name = "message_id", referencedColumnName = "message_id", insertable = false, updatable = false)                   
	private Message message;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int history_id;
	
	@NotNull
	private int message_id;
	
	@NotNull
	private Long sender_id;
	
	@NotNull
	private Long recipient_id;
	
	@Enumerated(EnumType.STRING) // In database it is stored as String
	private Status status;
	
	private LocalDateTime timestamp;
	
//	Getters and Setters	
	public int getHistory_id() {
		return history_id;
	}
	public void setHistory_id(int history_id) {
		this.history_id = history_id;
	}

	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public Long getSender_id() {
		return sender_id;
	}
	public void setSender_id(Long sender_id) {
		this.sender_id = sender_id;
	}
	
	public Long getReceiver_id() {
		return recipient_id;
	}
	public void setReceiver_id(Long receiver_id) {
		this.recipient_id = receiver_id;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}











