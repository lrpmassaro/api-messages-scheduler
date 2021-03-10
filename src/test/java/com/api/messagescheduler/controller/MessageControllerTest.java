package com.api.messagescheduler.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.api.messagescheduler.models.enums.MessageStatus;
import com.api.messagescheduler.repositories.MessageRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MessageControllerTest {

	@Autowired
	private MockMvc mock;
	
	MessageRepository repository;
	
	@Test
	public void must_create_schedule() throws Exception {
		mock.perform(post("/message")
				.content("{\"send_date\": \"15-03-2021 08:30:00\","
						+ " \"recipient\": \"16987654321\","
						+ " \"message\": \"Parabéns! Você foi aprovado!\","
						+ " \"type\": \"SMS\",")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.header().string("", containsString("")));
	}
	
	@Test
	public void must_not_create_schedule() throws Exception {
		mock.perform(post("/message")
				.content("{\"send_date\": \"15-03-2021 08:30:00\","
						+ " \"recipient\": \"16987654321\","
						+ " \"message\": \"Parabéns! Você foi aprovado!\","
						+ " \"type\": \"TELEGRAM\",")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$",
						is("Erro ao criar agendamento: preechimento incorreto.")));
	}
	
	@Test
	@Sql({"/insert.sql"})
	public void must_find_by_id() throws Exception {
		mock.perform(get("/message/{id}", 1L)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is("1")))
			.andExpect(jsonPath("$.schedule_date", is("2021-03-12 16:30:00")))
			.andExpect(jsonPath("$.send_date", is("2021-03-15 08:00:00")))
			.andExpect(jsonPath("$.recipient", is("lucas@email.com")))
			.andExpect(jsonPath("$.message", is("Parabéns! Você foi aprovado!")))
			.andExpect(jsonPath("$.status", is("PENDING")))
			.andExpect(jsonPath("$.type", is("EMAIL")));
	}
	
	@Test
	@Sql({"/insert.sql"})
	public void must_find_by_status() throws Exception {
		mock.perform(get("/messages/{status}", MessageStatus.PENDING)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is("1")))
			.andExpect(jsonPath("$.schedule_date", is("2021-03-10 08:30:00")))
			.andExpect(jsonPath("$.send_date", is("2021-03-15 08:30:00")))
			.andExpect(jsonPath("$.recipient", is("lucas@email.com")))
			.andExpect(jsonPath("$.message", is("Parabéns! Você foi aprovado!")))
			.andExpect(jsonPath("$.status", is("PENDING")))
			.andExpect(jsonPath("$.type", is("EMAIL")));
	}

	@Test
	@Sql({"/insert.sql"})
	public void must_delete_by_id() throws Exception {
		mock.perform(delete("/message/{id}", 1L)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

}
