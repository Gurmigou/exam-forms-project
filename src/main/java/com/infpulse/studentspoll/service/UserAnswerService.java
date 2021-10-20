package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.exceptions.NotFoundException;
import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.entity.UserAnswer;
import com.infpulse.studentspoll.model.formDto.UserAnswerDto;
import com.infpulse.studentspoll.model.formDto.FormHeader;
import com.infpulse.studentspoll.repository.UserAnswerRepository;
import com.infpulse.studentspoll.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAnswerService {

	private final UserAnswerRepository answerRepository;
	private final UserRepository userRepository;
	private final FormService formService;
	private final ModelMapper mapper;

	@Autowired
	public UserAnswerService(UserAnswerRepository answerRepository,
							 FormService formService,
							 UserRepository userRepository,
							 ModelMapper mapper) {
		this.answerRepository = answerRepository;
		this.formService = formService;
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	public UserAnswerDto getUserAnswer(long formId, String userName) {
		UserAnswer userAnswer = answerRepository
				.findUserAnswerByBelongsToForm_IdAndAnswerOwner_Login(formId, userName);
		return mapper.map(userAnswer, UserAnswerDto.class);
	}

	public UserAnswerDto saveAnswer(long formId, UserAnswerDto userAnswerDto, String userName) {
		User user = findUserByUsername(userName);
		UserAnswer userAnswer = mapper.map(userAnswerDto, UserAnswer.class);
		Form form = formService.getFormEntity(formId);
		userAnswer.setAnswerOwner(user);
		userAnswer.setBelongsToForm(form);
		return mapper.map(answerRepository.save(userAnswer), UserAnswerDto.class);
	}

	public List<FormHeader> getUserAnswers(String userName) {
		User user = findUserByUsername(userName);
		return answerRepository.getUserAnswers(user);
	}

	private User findUserByUsername(String userName) {
		return userRepository.findByLogin(userName).orElseThrow(() -> new NotFoundException("User " + userName));
	}
}