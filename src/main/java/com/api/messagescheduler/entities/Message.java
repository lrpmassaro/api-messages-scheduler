package com.api.messagescheduler.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.messagescheduler.entities.enums.MessageStatus;
import com.api.messagescheduler.entities.enums.MessageType;

@Entity
@Table(name = "message-scheduling")
public class Message {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "data_agendamento", nullable = false)
	private LocalDateTime dataAgendamento;

	@Column(name = "data_envio", nullable = false)
	private LocalDateTime dataEnvio;

	@Column(name = "destinatario", nullable = false)
	private String destinatario;

	@Column(name = "message", nullable = false)
	private String message;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private MessageStatus status;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private MessageType type;

	public Message() {
	}

	public Message(Long id, LocalDateTime dataAgendamento, LocalDateTime dataEnvio, String destinatario, String message,
			MessageStatus status, MessageType type) {
		this.id = id;
		this.dataAgendamento = LocalDateTime.now();
		this.dataEnvio = dataEnvio;
		this.destinatario = destinatario;
		this.message = message;
		this.status = MessageStatus.PENDENTE;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public LocalDateTime getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
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

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public void enviada() {
		this.status = MessageStatus.ENVIADA;
	}

	public void cancelada() {
		this.status = MessageStatus.CANCELADA;
	}

}
