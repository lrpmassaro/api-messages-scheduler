package com.api.messagescheduler.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.messagescheduler.dto.MessageDTO;
import com.api.messagescheduler.factory.MessageFactory;
import com.api.messagescheduler.models.Message;
import com.api.messagescheduler.models.enums.MessageStatus;
import com.api.messagescheduler.repositories.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public MessageDTO save(MessageDTO messageDTO) throws Exception {
		if(LocalDateTime.now().isAfter(messageDTO.getSendDate())) {
			throw new Exception("The scheduling date must be after than the current date.");
		}
		Message message = MessageFactory.toMessage(messageDTO);
		return MessageFactory.toMessageDTO(messageRepository.save(message));
	}
	
	public List<Message> findAll(){
		return messageRepository.findAll();
	}
	
	public Message findById(Long id) {
		return messageRepository.findById(id).get();
	}
	
	public List<Message> findByStatus(MessageStatus status){
		return messageRepository.findByStatus(status);
	}
	
	public void deleteById(Long id) {
		messageRepository.deleteById(id);
	}		
	
//	public void cancell(Long id) {
//		Message message = messageRepository.findById(id).get();
//		message.setStatus(MessageStatus.CANCELLED);
//	}
//	
//	public MessageDTO cancellMessage(Long id) throws Exception {
//		Message message = messageRepository.findById(id).orElseThrow(() -> new Exception("Schedule id" + id + "not found."));
//		message.setStatus(MessageStatus.CANCELLED);
//		return MessageFactory.toMessageDTO(messageRepository.save(message));
//	}
//	
//	public MessageDTO cancellById(Long id) throws Exception {
//		Message message = messageRepository.findById(id).orElseThrow(() -> new Exception("Schedule id" + id + "not found."));
//		message.setStatus(MessageStatus.CANCELLED);
//		return MessageFactory.toMessageDTO(message);
//	}

}
