package com.api.messagescheduler.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.messagescheduler.entities.Message;
import com.api.messagescheduler.repositories.MessageRepository;

@RestController
@RequestMapping(value = "/messages")
public class MessageResource {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@GetMapping
	public ResponseEntity<List<Message>> findAll(){
		List<Message> list = messageRepository.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Message> findById(@PathVariable Long id){
		Message msg = messageRepository.findById(id);
		return ResponseEntity.ok().body(msg);
	}
}
