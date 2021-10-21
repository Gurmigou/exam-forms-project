package com.infpulse.studentspoll.controller;

import com.infpulse.studentspoll.controllers.FormController;
import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.service.FormService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FormController.class)
public class FormControllerTest {

	protected final MockMvc mockMvc;
	protected final ObjectMapper mapper;

	@MockBean
	protected FormService formService;

	@Autowired
	public FormControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
		mapper = new ObjectMapper();
	}

	@Test
	public void shouldReturnStatusCreatedWhenNewFormCreated() throws Exception {
		Form form = new Form();


		mockMvc.perform(post("/api/forms/new")
						.contentType("application/json")
						.content(mapper.writeValueAsString(form)))
				.andExpect(status().isCreated());
	}

}
