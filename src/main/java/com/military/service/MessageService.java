package com.military.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.military.dto.InboxMessageDTO;
import com.military.dto.SendMessageDTO;
import com.military.encryption.EncryptionUtility;
import com.military.entity.Message;
import com.military.entity.MessageHistory;
import com.military.entity.Message.Status;
import com.military.exceptions.MessageNotFoundException;
import com.military.mapper.MessageMapper;
import com.military.repository.MessageHistoryRepo;
import com.military.repository.MessageRepo;
import com.military.repository.MessageSpecifications;

@Service @Transactional
public class MessageService {
	
	private MessageRepo messageRepo;
	private MessageHistoryRepo messageHistoryRepo;
	
	
	public MessageService(@Autowired MessageRepo messageRepo,@Autowired MessageHistoryRepo messageHistoryRepo){
		this.messageRepo = messageRepo;
		this.messageHistoryRepo = messageHistoryRepo;
	}
	
//	Inserting new message 
	public boolean sendMessage(SendMessageDTO dtoMessage) {
//		DO to entity mapping using external library (Message Mapper)
		Message entity = MessageMapper.INSTANCE.toEntity(dtoMessage);
		
//		Setting the encrypted message field
		entity.setEncryptedMessage(EncryptionUtility.encrypt(dtoMessage.messageContent()));                                            
		
//		Save to message table
		Message savedMessage = messageRepo.save(entity);
		
		MessageHistory historyEntity = new MessageHistory();
		historyEntity.setMessage_id(entity.getMessageId());
		historyEntity.setSender_id(entity.getSenderId());
		historyEntity.setReceiver_id(entity.getRecipientId());
		
		MessageHistory savedHistory = null;
		if(savedMessage != null)
			savedHistory = messageHistoryRepo.save(historyEntity);
		
		return savedMessage != null && savedHistory != null;
	}
	
//	Getting message by id
	public Message getMessageById(int messageId) {
		return messageRepo.findById(messageId).orElseThrow(() -> new MessageNotFoundException());
	}
	
//	getting messages in the inbox
	public List<InboxMessageDTO> getInboxMessages(int userId){
		return messageRepo.findByRecipientIdAndStatusOrderByTimestampDesc(userId, "Sent");
	}
	
//	marking message status as read
	public boolean markMessageAsRead(int messageId) {
		return messageRepo.markAsRead(messageId);
	}
	
//	Deleting messages
	public boolean deleteMessage(int messageId) {
		return messageRepo.deletingMessage(messageId);
	}
	
//	Getting all messages
	public List<Message> getAllMessages() {
		return messageRepo.findAll();
	}
	
//	Searching for a message using keyword
	public List<Message> searchMessages(Integer userId, String keyword, LocalDateTime startDate, LocalDateTime endDate,  Status readStatus) {
	    Specification<Message> spec = Specification
	    		.where(MessageSpecifications.hasUserId(userId))
	    		.and(MessageSpecifications.containsKeyword(keyword))
	    		.and(MessageSpecifications.isBetweenDates(startDate, endDate))
	    		.and(MessageSpecifications.hasStatus(readStatus));
		
		return messageRepo.findAll(spec);
	}
	
	
}

























