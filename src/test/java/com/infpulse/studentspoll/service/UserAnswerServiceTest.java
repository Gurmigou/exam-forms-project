package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.model.entity.Block;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.entity.UserAnswer;
import com.infpulse.studentspoll.model.formDto.FormHeader;
import com.infpulse.studentspoll.model.formDto.UserAnswerDto;
import com.infpulse.studentspoll.repository.UserAnswerRepository;
import com.infpulse.studentspoll.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.internal.matchers.Equality.areEqual;

@SpringBootTest(classes = UserAnswerService.class)
public class UserAnswerServiceTest {

	@MockBean
	protected UserAnswerRepository answerRepository;
	@MockBean
	protected UserRepository userRepository;
	@MockBean
	protected FormService formService;
	@SpyBean
	protected ModelMapper mapper;

	@Autowired
	UserAnswerService answerService;

	@Test
	public void shouldReturnUserAnswer() {
		Block block0 = new Block(1L, null, "question 1");
		Block block1 = new Block(1L, null, "question 2");
		Block block2 = new Block(1L, null, "question 3");
		List<Block> blockList = List.of(block0, block1, block2);

		UserAnswer userAnswer = new UserAnswer(1L, null, null
				, blockList, null);

		UserAnswerDto userAnswerDto = mapper.map(userAnswer, UserAnswerDto.class);

		when(answerRepository.findUserAnswerByBelongsToForm_IdAndAnswerOwner_Login(anyLong(), anyString())).thenReturn(userAnswer);

		areEqual(userAnswerDto, answerService.getUserAnswer(1L, "username"));
	}

	@Test
	public void shouldReturnPersistedAnswer() {
		User user = new User();
		user.setLogin("username");
		Block block0 = new Block(1L, null, "question 1");
		Block block1 = new Block(1L, null, "question 2");
		Block block2 = new Block(1L, null, "question 3");
		List<Block> blockList = List.of(block0, block1, block2);

		UserAnswer userAnswer = new UserAnswer(1L, null, null
				, blockList, null);

		UserAnswerDto userAnswerDto = mapper.map(userAnswer, UserAnswerDto.class);

		when(userRepository.findByLogin(any())).thenReturn(Optional.of(user));
		when(answerRepository.save(any())).thenReturn(userAnswer);
		when(formService.getFormEntity(anyLong())).thenReturn(null);

		areEqual(userAnswerDto, answerService.saveAnswer(1L, userAnswerDto, "username"));
	}

	@Test
	public void shouldReturnUserAnswers() {
		User user = new User();
		user.setLogin("username");
		FormHeader formHeader0 = new FormHeader("form 1",
				LocalDateTime.of(2020, 10, 23, 17, 0), 1L);
		FormHeader formHeader1 = new FormHeader("form 2",
				LocalDateTime.of(2020, 10, 25, 13, 25), 2L);
		FormHeader formHeader2 = new FormHeader("form 3",
				LocalDateTime.of(2020, 10, 29, 19, 56), 3L);
		List<FormHeader> formHeaders = List.of(formHeader0, formHeader1, formHeader2);

		when(answerRepository.getUserAnswers(any())).thenReturn(formHeaders);
		when(userRepository.findByLogin(any())).thenReturn(Optional.of(user));

		areEqual(formHeaders, answerService.getUserAnswers("username"));
	}

}
