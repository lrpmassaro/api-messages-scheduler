package com.api.messagescheduler.dto;

import java.time.LocalDateTime;

import com.api.messagescheduler.models.enums.MessageStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel()
public class MessageDTO {
	
	@JsonProperty("id")
	@ApiModelProperty(notes="Id message.", required = true)
	private Long id;
	
	@Builder.Default
	@JsonProperty("scheduleDate")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@ApiModelProperty(notes="Schedule date.")
	private LocalDateTime scheduleDate = LocalDateTime.now();
	
	@JsonProperty("sendDate")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@ApiModelProperty(notes="Send date and time", example ="15-12-2021 08:30:00", required = true)
	private LocalDateTime sendDate;
	
	@JsonProperty("recipient")
	@ApiModelProperty(notes="Recipient message.", example = "16987654321 for SMS, PUSH, WHATSAPP or lucas@email.com for EMAIL", required = true)
	private String recipient;
	
	@JsonProperty("message")
	@ApiModelProperty(notes="Message content.", example = "Happy birthday!", required = true)
	private String message;

	@Builder.Default
	@JsonProperty("status")
	@ApiModelProperty(notes="Status message.", allowableValues = "PENDING, SENDED, CENCELLED", required = true)
	private String status = MessageStatus.PENDING.toString();
	
	@JsonProperty("type")
	@ApiModelProperty(notes="Type message.", allowableValues = "SMS, EMAIL, PUSH, WHATSAPP", required = true)
	private String type;
}
