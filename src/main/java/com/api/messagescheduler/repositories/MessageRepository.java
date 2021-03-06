package com.api.messagescheduler.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.api.messagescheduler.entities.Message;

@Component
public class MessageRepository {
	
	private Map<Long, Message> map = new HashMap<>();
	
	public void save(Message message) {
		map.put(message.getId(), message);
	}
	
	public Message findById(Long id) {
		return map.get(id);
	}
	
	public List<Message> findAll(){
		return new ArrayList<Message>(map.values());
	}

}
