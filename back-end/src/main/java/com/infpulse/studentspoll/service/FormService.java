package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.exceptions.NoPermissionException;
import com.infpulse.studentspoll.exceptions.NotFoundException;
import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.entity.PossibleAnswer;
import com.infpulse.studentspoll.model.entity.Question;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.formDto.formHeaders.AvailableFormHeader;
import com.infpulse.studentspoll.model.formDto.formHeaders.OwnedFormHeader;
import com.infpulse.studentspoll.model.formDto.ownedFormDto.FormDto;
import com.infpulse.studentspoll.model.formDto.passedForm.PossibleAnswerDto;
import com.infpulse.studentspoll.model.formDto.passedForm.QuestionDto;
import com.infpulse.studentspoll.model.state.AnswerStatus;
import com.infpulse.studentspoll.repository.FormRepository;
import com.infpulse.studentspoll.repository.PossibleAnswerRepository;
import com.infpulse.studentspoll.repository.QuestionRepository;
import com.infpulse.studentspoll.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class FormService {

	private final PossibleAnswerRepository answerRepository;
	private final QuestionRepository questionRepository;
	private final FormRepository formsRepository;
	private final UserRepository userRepository;
	private final ModelMapper mapper;

	@Autowired
	public FormService(PossibleAnswerRepository answerRepository, QuestionRepository questionRepository,
	                   FormRepository formsRepository, UserRepository userRepository, ModelMapper mapper) {
		this.answerRepository = answerRepository;
		this.questionRepository = questionRepository;
		this.formsRepository = formsRepository;
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	public Form saveForm(FormDto formDto, java.lang.String email) {
		User user = findUserByEmail(email);
		Form form = mapper.map(formDto, Form.class);
		form.setOwner(user);
		form = formsRepository.save(form);
		saveQuestions(formDto.getQuestionDtoList(), form);
		return form;
	}

	public void saveQuestions(List<QuestionDto> questionsDto, Form form) {
		for (QuestionDto questionDto : questionsDto) {
			Question question = mapper.map(questionDto, Question.class);
			question.setOwnerForm(form);
			question = questionRepository.save(question);
			savePossibleAnswers(questionDto.getPossibleAnswersDto(), question);
		}
	}

	public void savePossibleAnswers(List<PossibleAnswerDto> possibleAnswersDto, Question question) {
		for (PossibleAnswerDto possibleAnswerDto : possibleAnswersDto) {
			PossibleAnswer answer = mapper.map(possibleAnswerDto, PossibleAnswer.class);
			boolean isCorrect = possibleAnswerDto.getAnswerStatus().equals(AnswerStatus.CORRECT);
			answer.setIsCorrect(isCorrect);
			answer.setQuestion(question);
			answerRepository.save(answer);
		}
	}

	public FormDto getForm(long formId) {
		Optional<Form> form = formsRepository.findById(formId);
		if (form.isPresent()) {
			FormDto formDto = mapper.map(form.get(), FormDto.class);
			formDto.setQuestionDtoList(getQuestions(form.get().getId()));
			return formDto;
		} else {
			return null;
		}
	}

	public List<QuestionDto> getQuestions(long formId) {
		List<QuestionDto> questionsDto = new LinkedList<>();
		// TODO: 19.11.2021 Uncomment
//		List<Question> questions = questionRepository.findAllByOwnerForm(formId);
//		for (Question question : questions) {
//			QuestionDto questionDto = mapper.map(question, QuestionDto.class);
//			questionDto.setPossibleAnswersDto(getPossibleAnswers(question));
//			questionsDto.add(questionDto);
//		}
		return questionsDto;
	}

	public List<PossibleAnswerDto> getPossibleAnswers(Question question) {
		List<PossibleAnswer> possibleAnswers = answerRepository.findAllByQuestion(question);
		return mapper.map(possibleAnswers, new TypeToken<PossibleAnswerDto>() {
		}.getType());
	}

	public List<OwnedFormHeader> getOwnedForms(java.lang.String email) {
		return formsRepository.getOwnedFormHeaders(email);
	}

	public List<AvailableFormHeader> getAvailableForms(java.lang.String email) {
		return formsRepository.getPassedFormHeader(email);
	}

	public Object getFormValuation(String name, long formId) {
		return formsRepository.getUserFormValuationList(name, formId);
	}

	public void deleteForm(long formId, java.lang.String email) {
		User user = findUserByEmail(email);
		Form form = formsRepository.findById(formId).orElseThrow(() -> new NotFoundException("Form " + formId));
		if (form.getOwner().getEmail().equals(user.getEmail())) {
			formsRepository.delete(form);
		} else {
			throw new NoPermissionException("Form " + formId + " for user " + user.getEmail());
		}
	}

	private User findUserByEmail(java.lang.String userName) {
		return userRepository.findByEmail(userName).orElseThrow(() -> new NotFoundException("User " + userName));
	}
}
