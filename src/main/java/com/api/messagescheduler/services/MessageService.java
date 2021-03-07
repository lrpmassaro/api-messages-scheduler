package com.api.messagescheduler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.messagescheduler.models.Message;
import com.api.messagescheduler.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
}
