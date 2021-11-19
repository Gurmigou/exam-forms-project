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
import java.util.ArrayList;
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
    private final PossibleAnswerRepository possibleAnswerRepository;

    @Autowired
    public UserAnswerService(AccountFormRepository accountFormRepository, UserAnswerRepository userAnswerRepository,
                             PossibleAnswerRepository answerRepository, QuestionRepository questionRepository,
                             FormRepository formsRepository, UserRepository userRepository,
                             PossibleAnswerRepository possibleAnswerRepository) {
        this.accountFormRepository = accountFormRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.formsRepository = formsRepository;
        this.userRepository = userRepository;
        this.possibleAnswerRepository = possibleAnswerRepository;
    }


    public AccountForm submitAnswer(SubmitAnswerDto submitAnswerDto, java.lang.String email) {
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

    public PassedFormDto getAnswer(long formId, java.lang.String email, LocalDateTime date) {
        AccountForm accountForm = accountFormRepository
                .findAccountFormByEmailAndFormIdAndDate(email, formId, date)
                .orElseThrow(() -> new NotFoundException("Account form for user - " + email + " and form "
                        + formId + " and date " + date));

        return buildPassedForm(accountForm);
    }

    private PassedFormDto buildPassedForm(AccountForm accountForm) {
        PassedFormDto passedFormDto = new PassedFormDto();
        passedFormDto.setTopicName(accountForm.getForm().getTopicName());
        passedFormDto.setAnswerDate(accountForm.getAnswerDate());
        passedFormDto.setFormScore(accountForm.getResultScore());
        passedFormDto.setFormQuestions(buildFormQuestions(accountForm));
        return passedFormDto;
    }

    private List<QuestionDto> buildFormQuestions(AccountForm accountForm) {
        List<Question> questions = questionRepository.findAllByOwnerForm(accountForm.getForm().getId());
        List<QuestionDto> questionDtoList = new ArrayList<>(questions.size());
        for (Question question : questions) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setQuestionName(question.getQuestionName());
            questionDto.setQuestionType(question.getQuestionType());
            questionDto.setPossibleAnswersDto(buildUserAnswers(accountForm.getId(), question));
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    private List<PossibleAnswerDto> buildUserAnswers(long accountFormId, Question question) {
        UserAnswer userAnswer = userAnswerRepository
                .findAnswerByAccountFormIdAndParentQuestionId(accountFormId, question.getId());
        List<PossibleAnswerDto> possibleAnswerDtoList;
        switch (question.getQuestionType()) {
            case SINGLE -> {
                if (checkIsEmpty(userAnswer.getAnswer())) {
                    possibleAnswerDtoList = Collections.emptyList();
                } else {
                    Long answerId = ((OptionalLong) userAnswer.getAnswer().getAnswer()).getAsLong();
                    PossibleAnswer answer = possibleAnswerRepository
                            .findPossibleAnswerByQuestionIdAndAnswerId(question.getId(), answerId);
                    PossibleAnswerDto possibleAnswerDto = new PossibleAnswerDto();
                    possibleAnswerDto.setAnswerValue(answer.getAnswerValue());
                    possibleAnswerDto.setPossibleAnswer(answer.getPossibleAnswer());
                    possibleAnswerDtoList = List.of(possibleAnswerDto);
                }
            }
            case MULTI -> {
                if (checkIsEmpty(userAnswer.getAnswer())) {
                    possibleAnswerDtoList = Collections.emptyList();
                } else {
                    List<Long> answersId = (List<Long>) userAnswer.getAnswer().getAnswer();
                    possibleAnswerDtoList = new ArrayList<>(answersId.size());
                    for (long answerId : answersId) {
                        PossibleAnswer answer = possibleAnswerRepository
                                .findPossibleAnswerByQuestionIdAndAnswerId(question.getId(), answerId);
                        PossibleAnswerDto possibleAnswerDto = new PossibleAnswerDto();
                        possibleAnswerDto.setAnswerValue(answer.getAnswerValue());
                        possibleAnswerDto.setPossibleAnswer(answer.getPossibleAnswer());
                        possibleAnswerDtoList.add(possibleAnswerDto);
                    }
                }
            }
            case OPEN -> {
                PossibleAnswerDto possibleAnswerDto = new PossibleAnswerDto();
                possibleAnswerDto.setPossibleAnswer((java.lang.String) userAnswer.getAnswer().getAnswer());
                possibleAnswerDtoList = List.of(possibleAnswerDto);
            }
            default -> possibleAnswerDtoList = Collections.emptyList();
        }
        return possibleAnswerDtoList;
    }

    @SuppressWarnings("unchecked")
    private boolean checkIsEmpty(UserAnswerObject<?> userAnswerObject) {
        if (userAnswerObject.getType().equals(QuestionType.SINGLE)) {
            {
                OptionalLong optionalLong = (OptionalLong) userAnswerObject.getAnswer();
                return optionalLong.isEmpty();
            }
        } else if (userAnswerObject.getType().equals(QuestionType.MULTI)) {
            List<Long> longs = (List<Long>) userAnswerObject.getAnswer();
            return longs.isEmpty();
        }
        return false;
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
                    .setAnswer(buildSingleUserAnswerObject(question.getId(), questionDto.getPossibleAnswersDto()));
            case MULTI -> userAnswer
                    .setAnswer(buildMultiUserAnswerObject(question.getId(), questionDto.getPossibleAnswersDto()));
            case OPEN -> {
                UserAnswerObject<java.lang.String> userAnswerObject = new UserAnswerObject<>();
                userAnswerObject.setType(QuestionType.OPEN);
                userAnswerObject.setAnswer(questionDto.getPossibleAnswersDto().get(0).getPossibleAnswer());
                userAnswer.setAnswer(userAnswerObject);
            }
        }
        userAnswerRepository.save(userAnswer);
    }

    private UserAnswerObject<OptionalLong> buildSingleUserAnswerObject(long questionId,
                                                                       List<PossibleAnswerDto> possibleAnswerDtoList) {
        UserAnswerObject<OptionalLong> userAnswerObject = new UserAnswerObject<>();
        userAnswerObject.setType(QuestionType.SINGLE);
        if (possibleAnswerDtoList.isEmpty()) {
            userAnswerObject.setAnswer(OptionalLong.empty());
        } else {
            PossibleAnswer possibleAnswer = possibleAnswerRepository
                    .findAnswerByQuestionIdAndPossibleAnswer(questionId, possibleAnswerDtoList.get(0)
                            .getPossibleAnswer()).orElseThrow();
            userAnswerObject.setAnswer(OptionalLong.of(possibleAnswer.getId()));
        }
        return userAnswerObject;
    }

    private UserAnswerObject<List<Long>> buildMultiUserAnswerObject(long questionId,
                                                                    List<PossibleAnswerDto> possibleAnswerDtoList) {
        UserAnswerObject<List<Long>> userAnswerObject = new UserAnswerObject<>();
        userAnswerObject.setType(QuestionType.MULTI);
        if (possibleAnswerDtoList.isEmpty()) {
            userAnswerObject.setAnswer(Collections.emptyList());
        } else {
            List<java.lang.String> possibleAnswersNames = possibleAnswerDtoList.stream()
                    .map(PossibleAnswerDto::getPossibleAnswer).collect(Collectors.toList());
            userAnswerObject.setAnswer(answerRepository
                    .findPossibleAnswersIdsByQuestionIdAndByPossibleAnswers(questionId, possibleAnswersNames));
        }
        return userAnswerObject;
    }

    private Question getQuestion(long formId, java.lang.String questionName) {
        return questionRepository
                .findQuestionByFormIdAndQuestionName(formId, questionName)
                .orElseThrow(() -> new NotFoundException("Question - " +
                        questionName + "for form - " + formId));
    }

    private User findUserByEmail(java.lang.String userName) {
        return userRepository.findByEmail(userName).orElseThrow(() -> new NotFoundException("User " + userName));
    }

    private Form getForm(long formId) {
        return formsRepository.findById(formId)
                .orElseThrow(() -> new NotFoundException("Form " + formId));
    }
}
