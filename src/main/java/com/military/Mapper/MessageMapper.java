package com.military.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.military.DTO.SendMessageDTO;
import com.military.Entity.Message;

@Mapper
public interface MessageMapper {
	
	MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

	@Mapping(target = "encryptedMessage", source = "message")
	Message toEntity(SendMessageDTO dto);
}
