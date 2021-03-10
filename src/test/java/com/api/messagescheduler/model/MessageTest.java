package com.api.messagescheduler.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.messagescheduler.models.Message;
import com.api.messagescheduler.models.enums.MessageStatus;

@RunWith(SpringRunner.class)
public class MessageTest {
	
	@Test
	public void must_create_message(){
		Message message = new Message();
		assertThat(message.getScheduleDate()).isNotNull().isInstanceOf(LocalDateTime.class);
		assertThat(message.getStatus()).isNotNull().isEqualTo(MessageStatus.PENDING);
	}

}
