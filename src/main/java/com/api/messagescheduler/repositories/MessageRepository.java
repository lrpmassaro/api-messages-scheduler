package com.api.messagescheduler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.messagescheduler.models.Message;
import com.api.messagescheduler.models.enums.MessageStatus;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
	
	List<Message> findByStatus(MessageStatus status);
}
