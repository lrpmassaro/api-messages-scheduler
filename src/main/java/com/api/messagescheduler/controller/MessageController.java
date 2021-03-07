package com.api.messagescheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.messagescheduler.models.Message;
import com.api.messagescheduler.repositories.MessageRepository;

@RestController
@RequestMapping("/api")
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;
	
	@PostMapping("/message")
	@ResponseStatus(HttpStatus.CREATED)
	public Message addMessage(@RequestBody Message message) {
		return messageRepository.save(message);
	}

	@GetMapping("/messages")
	@ResponseStatus(HttpStatus.OK)
	public List<Message> messagesList() {
		return messageRepository.findAll();
	}

	@GetMapping("/message/{id}")
	public Message findById(@PathVariable Long id) {
		return messageRepository.findById(id).get();
	}

	@DeleteMapping("/message/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		messageRepository.deleteById(id);
	}

}
