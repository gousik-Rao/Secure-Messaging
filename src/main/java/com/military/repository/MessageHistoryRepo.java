package com.military.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.military.entity.MessageHistory;

@Repository
public interface MessageHistoryRepo extends JpaRepository<MessageHistory, Integer>{
	
}
