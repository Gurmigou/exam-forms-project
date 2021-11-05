package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.exceptions.NotFoundException;
import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.entity.PossibleAnswer;
import com.infpulse.studentspoll.model.entity.Question;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.formDto.formHeaders.OwnedFormHeader;
import com.infpulse.studentspoll.model.formDto.formHeaders.PassedFormHeader;
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

	public void saveForm(FormDto formDto, String email) {
		User user = findUserByEmail(email);
		Form form = mapper.map(formDto, Form.class);
		form.setOwner(user);
		form = formsRepository.save(form);
		saveQuestions(formDto.getQuestionDtoList(), form);
	}

	public void saveQuestions(List<QuestionDto> questionsDto, Form form) {
		for (QuestionDto questionDto : questionsDto) {
			Question question = mapper.map(questionDto, Question.class);
			question.setParentForm(form);
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
		Form form = formsRepository.findById(formId)
				.orElseThrow(() -> new NotFoundException("Form " + formId));
		FormDto formDto = mapper.map(form, FormDto.class);
		formDto.setQuestionDtoList(getQuestions(form));
		return formDto;
	}

	public List<QuestionDto> getQuestions(Form form) {
		List<QuestionDto> questionsDto = new LinkedList<>();
		List<Question> questions = questionRepository.findAllByForm(form);
		for (Question question : questions) {
			QuestionDto questionDto = mapper.map(question, QuestionDto.class);
			questionDto.setPossibleAnswersDto(getPossibleAnswers(question));
			questionsDto.add(questionDto);
		}
		return questionsDto;
	}

	public List<PossibleAnswerDto> getPossibleAnswers(Question question) {
		List<PossibleAnswer> possibleAnswers = answerRepository.findAllByQuestion(question);
		return mapper.map(possibleAnswers, new TypeToken<PossibleAnswerDto>() {
		}.getType());
	}

    public List<OwnedFormHeader> getOwnedForms(String email) {
        return formsRepository.getOwnedFormHeaders(email);
    }

    public List<PassedFormHeader> getAvailableForms(String email) {
        return formsRepository.getPassedFormHeader(email);
    }

   /*

    private FormDto saveForm(FormDto formDto, User user) {
        Form form = mapper.map(formDto, Form.class);
        form.setOwner(user);
        return mapper.map(formsRepository.save(form), FormDto.class);
    }

    public FormDto updateForm(long formId, FormDto formDto, String userName) {
        User user = findUserByUsername(userName);
        Optional<Form> oldForm = formsRepository.findById(formId);
        if (oldForm.isPresent()) {
            Form form = oldForm.get();
            if (!form.getOwner().equals(user) || form.getIsDeleted()) {
                throw new NoPermissionException("Form " + oldForm.get().getId());
            }
            mapper.map(formDto, form);
            return mapper.map(formsRepository.save(form), FormDto.class);
        } else {
            return saveForm(formDto, user);
        }
    }

    public FormDto getForm(long formId) {
        return mapper.map(getFormEntity(formId), FormDto.class);
    }

    public Form getFormEntity(long formId) {
        Form form = formsRepository.findById(formId).orElseThrow(() -> new NotFoundException("Form " + formId));
        if (form.getIsDeleted()) {
            throw new NotFoundException("Form " + formId);
        }
        return form;
    }

    public void deleteForm(long formId, String userName) {
        Form form = formsRepository.findById(formId).orElseThrow(() -> new NotFoundException("Form " + formId));
        if (form.getOwner().getLogin().equals(userName)) {
            formsRepository.setDeleted(formId);
        } else {
            throw new NoPermissionException("Form " + formId);
        }
    }

    public List<FormDto> getOwnedForms(String userName) {
        User user = findUserByUsername(userName);
        List<Form> forms = formsRepository.findAllByOwner(user).stream()
                .filter(form -> !form.getIsDeleted())
                .collect(Collectors.toList());
        return mapper.map(forms, new TypeToken<List<FormDto>>() {}.getType());
    }
    */

	private User findUserByEmail(String userName) {
		return userRepository.findByEmail(userName).orElseThrow(() -> new NotFoundException("User " + userName));
	}
}
