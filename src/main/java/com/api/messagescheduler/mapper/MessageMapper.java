package com.api.messagescheduler.mapper;

import com.api.messagescheduler.dto.MessageDTO;
import com.api.messagescheduler.models.Message;
import com.api.messagescheduler.models.enums.MessageStatus;
import com.api.messagescheduler.models.enums.MessageType;

public class MessageMapper {

	public static Message toMessage(MessageDTO messageDTO) {
		return Message.builder()
				.id(messageDTO.getId())
				.scheduleDate(messageDTO.getScheduleDate())
				.sendDate(messageDTO.getSendDate())
				.recipient(messageDTO.getRecipient())
				.message(messageDTO.getMessage())
				.status(MessageStatus.valueOf(messageDTO.getStatus()))
				.type(MessageType.valueOf(messageDTO.getType()))
				.build();
	}
	
	public static MessageDTO toMessageDTO(Message message) {
		return MessageDTO.builder()
				.id(message.getId())
				.scheduleDate(message.getScheduleDate())
				.sendDate(message.getSendDate())
				.recipient(message.getRecipient())
				.message(message.getMessage())
				.status(message.getStatus().name())
				.type(message.getType().name())
				.build();
	}

}
