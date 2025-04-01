package com.military.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.military.Entity.MessageHistory;

@Repository
public interface MessageHistoryRepo extends JpaRepository<MessageHistory, Integer>{
	
}
