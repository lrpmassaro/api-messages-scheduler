package com.api.messagescheduler.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.messagescheduler.models.enums.MessageStatus;
import com.api.messagescheduler.models.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "tb_message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime scheduleDate;

	@NotNull
	private LocalDateTime sendDate;

	@NotNull
	private String recipient;

	@NotNull
	private String message;

	@Enumerated(EnumType.STRING)
	private MessageStatus status;

	@NotNull
	@Enumerated(EnumType.STRING)
	private MessageType type;

	public Message() {
		this.status = MessageStatus.PENDING;
		this.scheduleDate = LocalDateTime.now();
	}
}
