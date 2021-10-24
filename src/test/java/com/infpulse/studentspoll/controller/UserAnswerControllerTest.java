package com.infpulse.studentspoll.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infpulse.studentspoll.controllers.UserAnswerController;
import com.infpulse.studentspoll.model.entity.Block;
import com.infpulse.studentspoll.model.formDto.FormHeader;
import com.infpulse.studentspoll.model.formDto.UserAnswerDto;
import com.infpulse.studentspoll.service.UserAnswerService;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserAnswerController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {
		SecurityAutoConfiguration.class,
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class
})
public class UserAnswerControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected UserAnswerService userAnswerService;

	protected static ObjectMapper mapper;
	protected Principal mockPrincipal;
	protected UserAnswerDto answerDto;

	@BeforeAll
	static void setup() {
		mapper = new ObjectMapper();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		mapper.registerModule(javaTimeModule);
	}

	@BeforeEach
	public void beforeEach() {
		mockPrincipal = Mockito.mock(Principal.class);
		when(mockPrincipal.getName()).thenReturn("username");

		Block block1 = new Block();
		block1.setQuestionName("q1");
		Block block2 = new Block();
		block2.setQuestionName("q2");
		Block block3 = new Block();
		block3.setQuestionName("q3");

		answerDto = new UserAnswerDto();
		answerDto.setListOfUsersBlocks(List.of(block1, block2, block3));
	}

	@Test
	public void shouldReturnStatusCreatedAndPersistedAnswers() throws Exception {
		when(userAnswerService.saveAnswer(anyLong(), any(), any())).thenReturn(answerDto);

		mockMvc.perform(post("/api/forms/{id}", 1)
						.principal(mockPrincipal)
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(answerDto)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(mapper.writeValueAsString(answerDto)));
	}

	@Test
	public void shouldReturnRequestedAnswer() throws Exception {
		when(userAnswerService.getUserAnswer(anyLong(), any())).thenReturn(answerDto);

		mockMvc.perform(get("/api/form-view/{id}/{username}", 1, "u"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(mapper.writeValueAsString(answerDto)));
	}

	@Test
	public void shouldReturnListOfPassedForms() throws Exception {
		FormHeader formHeader1 = new FormHeader("form1", LocalDateTime.of(2020, 10, 24, 15, 0), 1L);
		FormHeader formHeader2 = new FormHeader("form2", LocalDateTime.of(2020, 10, 25, 15, 0), 2L);
		FormHeader formHeader3 = new FormHeader("form3", LocalDateTime.of(2020, 10, 26, 15, 0), 3L);

		List<FormHeader> formHeaders = List.of(formHeader1, formHeader2, formHeader3);

		when(userAnswerService.getUserAnswers(any())).thenReturn(formHeaders);

		mockMvc.perform(get("/api/forms-answers")
						.principal(mockPrincipal))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.[0].name").value(formHeader1.getName()))
				.andExpect(jsonPath("$.[1].id").value(formHeader2.getId()))
				.andExpect(jsonPath("$.[2].passedDate")
						.value(formHeader3.getPassedDate()
								.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))));
	}

}
