package com.military.Entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity @Table(name = "message")
public class Message {

	@OneToMany(mappedBy = "message")
	private List<MessageHistory> messageHistories;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private int messageId;
	
	@NotNull @Column(name = "sender_id")
	private Long senderId;
	
	@NotNull @Column(name = "recipient_id")
	private Long recipientId;
	
	@NotBlank(message = "Field required") @Column(name = "message_content")
	private String messageContent;
	
	@Column(name = "encrypted_message") @NotBlank
	private String encryptedMessage;
	
	@Enumerated(EnumType.STRING) // In database it is stored as String
	private Status status;
	
	private Timestamp timestamp;
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public Long getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(Long recipient_id) {
		this.recipientId = recipient_id;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getEncryptedMessage() {
		return encryptedMessage;
	}
	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Timestamp getTimeStamp() {
		return timestamp;
	}
	public void setTimeStamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public enum Status{
		Sent,
		Read,
		Decrypted;
	}

	@Override
	public String toString() {
		return "Message [messageHistories=" + messageHistories + ", messageId=" + messageId + ", senderId=" + senderId
				+ ", recipientId=" + recipientId + ", messageContent=" + messageContent + ", encryptedMessage="
				+ encryptedMessage + ", status=" + status + ", timestamp=" + timestamp + "]";
	}
	
	
	
}
