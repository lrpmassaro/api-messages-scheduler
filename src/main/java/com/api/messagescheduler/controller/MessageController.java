package com.api.messagescheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.messagescheduler.models.Message;
import com.api.messagescheduler.models.enums.MessageStatus;
import com.api.messagescheduler.services.MessageService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@ApiOperation(value="Schedule new message")
	@PostMapping("/message")
	public ResponseEntity<Message> schedule(@RequestBody Message message) throws Exception {
		return new ResponseEntity<Message>(messageService.save(message), HttpStatus.CREATED);
	}

	@ApiOperation(value="Messages list")
	@GetMapping("/messages")
	public List<Message> findAll() {
		return messageService.findAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="Find message by ID")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Message finded.")
	})
	@GetMapping("/message/{id}")
	public Message findById(@PathVariable Long id) {
		return messageService.findById(id);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="Delete message by ID")
	@ApiResponses(value= {
			@ApiResponse(code=204, message="Message deleted successfully.")
	})
	@DeleteMapping("/message/{id}")
	public void deleteById(@PathVariable Long id) {
		messageService.deleteById(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="Find message by Status")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Messages finded by Status.")
	})
	@GetMapping("/messages/{status}")
	public List<Message> findByStatus(@PathVariable MessageStatus status) {
		return messageService.findByStatus(status);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="Cancell message by ID")
	@ApiResponses(value= {
			@ApiResponse(code=204, message="Message cancelled successfully.")
	})
	@PatchMapping("/message/{id}")
	public void cancellById(@PathVariable Long id) {
		messageService.cancellById(id);
	}
}
