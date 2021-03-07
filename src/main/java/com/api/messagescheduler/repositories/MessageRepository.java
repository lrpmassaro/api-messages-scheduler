package com.api.messagescheduler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.messagescheduler.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
}
