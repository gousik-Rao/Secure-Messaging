package com.military.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.military.DTO.InboxMessageDTO;
import com.military.Entity.Message;

@Repository @Transactional
public interface MessageRepo extends JpaRepository<Message, Integer>, JpaSpecificationExecutor<Message>{

	List<InboxMessageDTO> findByRecipientIdAndStatusOrderByTimestampDesc(int recipientId, String status);
	
	@Modifying 
	@Query("update Message m set m.status = 'READ' where m.id =: messageId")
	boolean markAsRead(int messageId);
	
	@Modifying
	@Query("delete from Message m where m.id =: messageId")
	boolean deletingMessage(int messageId);

	
	
//	Searching messages
	 @Query("SELECT m FROM Message m WHERE " +
	           "(:userId IS NULL OR m.senderId = :userId) AND " +
	           "(:keyword IS NULL OR m.messageContent LIKE %:keyword%) AND " +
	           "(:startDate IS NULL OR m.timestamp >= :startDate) AND " +
	           "(:endDate IS NULL OR m.timestamp <= :endDate) AND " +
	           "(:status IS NULL OR m.status = :status)")
	List<Message> findMessagesWithFilters(
			@Param("userId") Long userId,
            @Param("keyword") String keyword,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("status") String status);
	
}


















