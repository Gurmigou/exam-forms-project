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
import com.infpulse.studentspoll.model.state.FormState;
import com.infpulse.studentspoll.repository.FormRepository;
import com.infpulse.studentspoll.repository.PossibleAnswerRepository;
import com.infpulse.studentspoll.repository.QuestionRepository;
import com.infpulse.studentspoll.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<Form> saveForm(FormDto formDto, String email) {
        User user = findUserByEmail(email);
        Form form = mapper.map(formDto, Form.class);
        form.setOwner(user);
        form.setFormState(FormState.CREATED);
        form.setFormMaxResult(countMaxResultFromTheFormDto(formDto));
        form = formsRepository.save(form);
        saveQuestions(formDto.getQuestionDtoList(), form);
        return Optional.of(form);
    }

    private Integer countMaxResultFromTheFormDto(FormDto formDto) {
        return formDto.getQuestionDtoList().stream()
                .flatMap(questionDto -> questionDto.getPossibleAnswersDto().stream())
                .filter(possibleAnswerDto -> possibleAnswerDto.getAnswerStatus() == AnswerStatus.CORRECT)
                .mapToInt(PossibleAnswerDto::getAnswerValue)
                .sum();
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

    public Optional<FormDto> getForm(long formId) {
        Optional<Form> form = formsRepository.findById(formId);
        if (form.isPresent()) {
            if (form.get().getFormState() != FormState.DELETED ||
                    form.get().getFormState() != FormState.SUSPENDED) {
                FormDto formDto1 = FormDto.builder()
                        .attempts(form.get().getAttempts())
                        .expireDateTime(form.get().getExpireDateTime())
                        .topicName(form.get().getTopicName())
                        .questionDtoList(getQuestions(form.get().getId()))
                        .build();
                return Optional.of(formDto1);
            }
        }
        return Optional.empty();
    }

    public List<QuestionDto> getQuestions(long formId) {
        List<QuestionDto> questionsDto = new ArrayList<>();
        List<Question> questions = questionRepository.findAllByOwnerForm(formId);
        for (Question question : questions) {
            QuestionDto questionDto = QuestionDto.builder()
                    .questionName(question.getQuestionName())
                    .questionType(question.getQuestionType())
                    .possibleAnswersDto(getPossibleAnswerDtoFromTheQuestion(question))
                    .build();
            questionsDto.add(questionDto);
        }
        return questionsDto;
    }

    private List<PossibleAnswerDto> getPossibleAnswerDtoFromTheQuestion(Question question) {
        return answerRepository.findAllByQuestion(question).stream()
                .map(possibleAnswer -> PossibleAnswerDto.builder()
                        .answerStatus(AnswerStatus.DEFAULT)
                        .possibleAnswer(possibleAnswer.getPossibleAnswer())
                        .answerValue(possibleAnswer.getAnswerValue())
                        .build()).collect(Collectors.toList());
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

    public void deleteForm(long formId, String email) {
        User user = findUserByEmail(email);
        Form form = formsRepository.findById(formId).orElseThrow(() -> new NotFoundException("Form " + formId));
        if (form.getOwner().getEmail().equals(user.getEmail())) {
            form.setFormState(FormState.DELETED);
            formsRepository.save(form);
        } else {
            throw new NoPermissionException("Form " + formId + " for user " + user.getEmail());
        }
    }

    private User findUserByEmail(java.lang.String userName) {
        return userRepository.findByEmail(userName).orElseThrow(() -> new NotFoundException("User " + userName));
    }
}
