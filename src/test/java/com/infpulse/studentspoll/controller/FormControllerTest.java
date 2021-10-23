package com.infpulse.studentspoll.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infpulse.studentspoll.controllers.FormController;
import com.infpulse.studentspoll.model.entity.Block;
import com.infpulse.studentspoll.model.formDto.FormDto;
import com.infpulse.studentspoll.service.FormService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FormController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {
		SecurityAutoConfiguration.class,
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class
})
public class FormControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected FormService formService;

	protected static ObjectMapper mapper;
	protected FormDto formDto;
	protected Principal mockPrincipal;

	@BeforeAll
	static void setup() {
		mapper = new ObjectMapper();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		mapper.registerModule(javaTimeModule);
	}

	@BeforeEach
	public void beforeEach() {
		formDto = new FormDto();
		formDto.setTopicName("form name");
		LocalDateTime localDateTime = LocalDateTime.of(2022, 10, 23, 17, 0);
		formDto.setExpireDate(localDateTime);
		formDto.setListOfBlocks(List.of(new Block()));

		mockPrincipal = Mockito.mock(Principal.class);
		when(mockPrincipal.getName()).thenReturn("username");
	}

	@Test
	public void shouldReturnStatusCreatedAndCreatedForm() throws Exception {
		when(formService.saveForm(any(), any())).thenReturn(formDto);

		mockMvc.perform(post("/api/forms/new")
						.principal(mockPrincipal)
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(formDto)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.topicName").value(formDto.getTopicName()));
	}

	@Test
	public void shouldReturnRequestedForm() throws Exception {
		when(formService.getForm(19)).thenReturn(formDto);

		mockMvc.perform(get("/api/forms/{formId}", "19"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.topicName").value(formDto.getTopicName()));
	}

	@Test
	public void shouldReturnOkWhenDeleted() throws Exception {
		mockMvc.perform(delete("/api/forms/{formId}", "5")
						.principal(mockPrincipal))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldReturnOkAndUpdatedForm() throws Exception {
		when(formService.updateForm(anyLong(), any(), any())).thenReturn(formDto);

		mockMvc.perform(put("/api/forms/{formId}", 0)
						.principal(mockPrincipal)
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(formDto)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.topicName").value(formDto.getTopicName()));
	}

	@Test
	public void shouldReturnFromsList() throws Exception {
		FormDto formDto0 = new FormDto();
		formDto0.setTopicName("form 0");
		FormDto formDto1 = new FormDto();
		formDto1.setTopicName("form 1");
		FormDto formDto2 = new FormDto();
		formDto2.setTopicName("form 2");
		List<FormDto> formDtos = List.of(formDto0, formDto1, formDto2);

		when(formService.getOwnedForms(any())).thenReturn(formDtos);

		mockMvc.perform(get("/api/forms-owned")
						.principal(mockPrincipal))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(mapper.writeValueAsString(formDtos)));
	}

}
