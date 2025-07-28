package com.military.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.military.dto.SendMessageDTO;
import com.military.entity.Message;

@Mapper
public interface MessageMapper {
	
	MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

	@Mapping(target = "encryptedMessage", source = "message")
	Message toEntity(SendMessageDTO dto);
}
