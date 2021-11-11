package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.exceptions.NotFoundException;
import com.infpulse.studentspoll.model.entity.*;
import com.infpulse.studentspoll.model.formDto.passedForm.PassedFormDto;
import com.infpulse.studentspoll.model.formDto.passedForm.PossibleAnswerDto;
import com.infpulse.studentspoll.model.formDto.passedForm.QuestionDto;
import com.infpulse.studentspoll.model.formDto.submitForm.SubmitAnswerDto;
import com.infpulse.studentspoll.model.state.FormState;
import com.infpulse.studentspoll.model.state.QuestionType;
import com.infpulse.studentspoll.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

@Service
public class UserAnswerService {

	private final AccountFormRepository accountFormRepository;
	private final UserAnswerRepository userAnswerRepository;
	private final PossibleAnswerRepository answerRepository;
	private final QuestionRepository questionRepository;
	private final FormRepository formsRepository;
	private final UserRepository userRepository;

	@Autowired
	public UserAnswerService(AccountFormRepository accountFormRepository, UserAnswerRepository userAnswerRepository,
	                         PossibleAnswerRepository answerRepository, QuestionRepository questionRepository,
	                         FormRepository formsRepository, UserRepository userRepository) {
		this.accountFormRepository = accountFormRepository;
		this.userAnswerRepository = userAnswerRepository;
		this.answerRepository = answerRepository;
		this.questionRepository = questionRepository;
		this.formsRepository = formsRepository;
		this.userRepository = userRepository;
	}


	public AccountForm submitAnswer(SubmitAnswerDto submitAnswerDto, String email) {
		User user = findUserByEmail(email);
		Form form = getForm(submitAnswerDto.getFormId());
		AccountForm accountForm = AccountForm.builder()
				.user(user)
				.form(form)
				.formState(FormState.CREATED)
				.build();
		accountForm = accountFormRepository.save(accountForm);
		parseAnswersList(submitAnswerDto.getQuestionDtoList(), form, accountForm);
		return accountForm;
	}

	public PassedFormDto getAnswer(long formId, String email, LocalDateTime date) {
		Form form = getForm(formId);
		List<UserAnswer> userAnswers = userAnswerRepository.findAnswersByEmailAndDate(email, date);
		return buildPassedForm(userAnswers, form);
	}

	private PassedFormDto buildPassedForm(List<UserAnswer> userAnswers, Form form) {
		for (UserAnswer userAnswer : userAnswers) {

		}
	}

	private void parseAnswersList(List<QuestionDto> questionDtoList, Form form, AccountForm accountForm) {
		for (QuestionDto questionDto : questionDtoList) {
			Question question = getQuestion(form.getId(), questionDto.getQuestionName());
			saveUserAnswer(questionDto, question, accountForm);
		}
	}

	private void saveUserAnswer(QuestionDto questionDto, Question question, AccountForm accountForm) {
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setParentQuestion(question);
		userAnswer.setAccountForm(accountForm);
		switch (question.getQuestionType()) {
			case SINGLE -> userAnswer
					.setAnswer(singleUserAnswerObject(question.getId(), questionDto.getPossibleAnswersDto()));
			case MULTI -> userAnswer
					.setAnswer(multiUserAnswerObject(question.getId(), questionDto.getPossibleAnswersDto()));
			case OPEN -> {
				UserAnswerObject<String> userAnswerObject = new UserAnswerObject<>();
				userAnswerObject.setType(QuestionType.OPEN);
				userAnswerObject.setAnswer(questionDto.getPossibleAnswersDto().get(0).getPossibleAnswer());
				userAnswer.setAnswer(userAnswerObject);
			}
		}
		userAnswerRepository.save(userAnswer);
	}

	private UserAnswerObject<OptionalLong> singleUserAnswerObject(long questionId,
	                                                              List<PossibleAnswerDto> possibleAnswerDtoList) {
		UserAnswerObject<OptionalLong> userAnswerObject = new UserAnswerObject<>();
		userAnswerObject.setType(QuestionType.SINGLE);
		if (possibleAnswerDtoList.isEmpty()) {
			userAnswerObject.setAnswer(OptionalLong.empty());
		} else {
			PossibleAnswer possibleAnswer = userAnswerRepository
					.findAnswerByQuestionIdAndPossibleAnswer(questionId, possibleAnswerDtoList.get(0));
			userAnswerObject.setAnswer(OptionalLong.of(possibleAnswer.getId()));
		}
		return userAnswerObject;
	}

	private UserAnswerObject<List<Long>> multiUserAnswerObject(long questionId,
	                                                           List<PossibleAnswerDto> possibleAnswerDtoList) {
		UserAnswerObject<List<Long>> userAnswerObject = new UserAnswerObject<>();
		userAnswerObject.setType(QuestionType.MULTI);
		if (possibleAnswerDtoList.isEmpty()) {
			userAnswerObject.setAnswer(Collections.emptyList());
		} else {
			List<String> possibleAnswersNames = possibleAnswerDtoList.stream()
					.map(PossibleAnswerDto::getPossibleAnswer).collect(Collectors.toList());
			userAnswerObject.setAnswer(answerRepository
					.findPossibleAnswersIdsByQuestionIdAndPossibleAnswers(questionId, possibleAnswersNames));
		}
		return userAnswerObject;
	}

	private Question getQuestion(long formId, String questionName) {
		return questionRepository
				.findQuestionByFormIdAndQuestionName(formId, questionName)
				.orElseThrow(() -> new NotFoundException("Not found question - " +
						questionName + "for form - " + formId));
	}

	private User findUserByEmail(String userName) {
		return userRepository.findByEmail(userName).orElseThrow(() -> new NotFoundException("User " + userName));
	}

	private Form getForm(long formId) {
		return formsRepository.findById(formId)
				.orElseThrow(() -> new NotFoundException("Form " + formId));
	}
}
