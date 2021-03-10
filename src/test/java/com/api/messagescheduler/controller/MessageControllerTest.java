package com.api.messagescheduler.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.api.messagescheduler.models.enums.MessageStatus;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void must_create_schedule() throws Exception {
		mockMvc.perform(post("/message")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"sendDate\": \"15-03-2021 08:30:00\","
						+ " \"recipient\": \"16987654321\","
						+ " \"message\": \"Parabéns! Você foi aprovado!\","
						+ " \"type\": \"SMS\","));
	}
	
	@Test
	public void must_not_create_schedule() throws Exception {
		mockMvc.perform(post("/message")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"sendDate\": \"15-03-2021 08:30:00\","
						+ " \"recipient\": \"16987654321\","
						+ " \"message\": \"Parabéns! Você foi aprovado!\","
						+ " \"type\": \"TELEGRAM\","));
	}
	
	@Test
	public void must_find_by_id() throws Exception {
		mockMvc.perform(get("/message/{id}", 1L));
	}
	
	@Test
	public void must_find_by_status() throws Exception {
		mockMvc.perform(get("/messages/{status}", MessageStatus.PENDING));
	}

	@Test
	@Sql({"/insert.sql"})
	public void must_delete_by_id() throws Exception {
		mockMvc.perform(delete("/message/{id}", 1L));
	}

}
