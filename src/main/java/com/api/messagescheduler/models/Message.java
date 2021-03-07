package com.api.messagescheduler.models;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
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

@Entity
@Table(name = "tb_message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "schedule_date", nullable = false)
	private LocalDateTime scheduleDate;

	@Column(name = "send_date", nullable = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sendDate;

	@Column(name = "recipient", nullable = false)
	private String recipient;

	@Column(name = "message", nullable = false)
	private String message;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private MessageStatus status;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private MessageType type;

	public Message() {
		this.status = MessageStatus.PENDING;
		this.scheduleDate = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(LocalDateTime scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void Date(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public Message setStatusSended() {
		this.setStatus(MessageStatus.SENDED);
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
