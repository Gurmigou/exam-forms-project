package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.exceptions.NotFoundException;
import com.infpulse.studentspoll.model.entity.*;
import com.infpulse.studentspoll.model.formDto.passedForm.PassedFormDto;
import com.infpulse.studentspoll.model.formDto.passedForm.PossibleAnswerDto;
import com.infpulse.studentspoll.model.formDto.passedForm.QuestionDto;
import com.infpulse.studentspoll.model.formDto.submitForm.SubmitAnswerDto;
import com.infpulse.studentspoll.model.state.AnswerStatus;
import com.infpulse.studentspoll.model.state.FormState;
import com.infpulse.studentspoll.model.state.QuestionType;
import com.infpulse.studentspoll.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
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

    public AccountForm submitAnswer(SubmitAnswerDto submitAnswerDto, String email) {
        User user = findUserByEmail(email);
        Form form = getForm(submitAnswerDto.getFormId());
        if (form.getFormState() == FormState.CREATED) {
            form.setFormState(FormState.PASSED);
        }
        AccountForm accountForm = AccountForm.builder()
                .user(user)
                .form(form)
                .build();
        accountForm = accountFormRepository.save(accountForm);
        parseAnswersList(submitAnswerDto.getQuestionDtoList(), form, accountForm);
        return accountForm;
    }

    public PassedFormDto getAnswer(long formId, String email, LocalDateTime date) {
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
        List<PossibleAnswer> questionAnswers = possibleAnswerRepository.findAllByQuestion(question);
        List<PossibleAnswerDto> possibleAnswerDtoList = new ArrayList<>();
        switch (question.getQuestionType()) {
            case SINGLE, MULTI -> getFromSingleMultiAnswers(userAnswer, questionAnswers, possibleAnswerDtoList);
            case OPEN -> getFromOpen(userAnswer, questionAnswers, possibleAnswerDtoList);
            default -> possibleAnswerDtoList = Collections.emptyList();
        }
        return possibleAnswerDtoList;
    }

    private void getFromOpen(UserAnswer userAnswer,
                             List<PossibleAnswer> questionAnswers,
                             List<PossibleAnswerDto> possibleAnswerDtoList) {
        questionAnswers.stream().map(qAnswer -> PossibleAnswerDto.builder()
                .answerStatus(createOpenAnswerStatus(qAnswer, userAnswer))
                .possibleAnswer((String) userAnswer.getAnswer().getAnswer())
                .answerValue(qAnswer.getAnswerValue())
                .build())
                .forEach(possibleAnswerDtoList::add);
    }

    private void getFromSingleMultiAnswers(UserAnswer userAnswer,
                                           List<PossibleAnswer> questionAnswers,
                                           List<PossibleAnswerDto> possibleAnswerDtoList) {
        if (checkIsEmpty(userAnswer.getAnswer())) {
            getEmptyAnswerDtoList(questionAnswers, possibleAnswerDtoList);
        } else {
            questionAnswers.stream().map(qAnswer -> PossibleAnswerDto.builder()
                            .answerStatus(createAnswerStatus(qAnswer, userAnswer))
                            .answerValue(qAnswer.getAnswerValue())
                            .possibleAnswer(qAnswer.getPossibleAnswer())
                            .build())
                    .forEach(possibleAnswerDtoList::add);
        }
    }

    private AnswerStatus createOpenAnswerStatus(PossibleAnswer qAnswer, UserAnswer userAnswer) {
        String usersAnswer = (String) userAnswer.getAnswer().getAnswer();
        return Objects.equals(qAnswer.getPossibleAnswer().toLowerCase().trim(),
                usersAnswer.toLowerCase().trim()) ? AnswerStatus.USER_CORRECT : AnswerStatus.WRONG;
    }

    @SuppressWarnings("unchecked")
    private AnswerStatus createAnswerStatus(PossibleAnswer qAnswer, UserAnswer userAnswer) {
        var userAnswers = (List<Long>) userAnswer.getAnswer().getAnswer();
        if (qAnswer.getIsCorrect()) {
            if (userAnswers.contains(qAnswer.getId())) {
                return AnswerStatus.USER_CORRECT;
            }
            return AnswerStatus.CORRECT;
        }
        if (userAnswers.contains(qAnswer.getId())) {
            return AnswerStatus.WRONG;
        }
        return AnswerStatus.DEFAULT;
    }

    private void getEmptyAnswerDtoList(List<PossibleAnswer> questionAnswers,
                                       List<PossibleAnswerDto> possibleAnswerDtoList) {
        questionAnswers.stream().map(qAnswer -> PossibleAnswerDto.builder()
                        .answerStatus(qAnswer.getIsCorrect() ? AnswerStatus.CORRECT : AnswerStatus.DEFAULT)
                        .answerValue(qAnswer.getAnswerValue())
                        .possibleAnswer(qAnswer.getPossibleAnswer())
                        .build())
                .forEach(possibleAnswerDtoList::add);
    }

    @SuppressWarnings("unchecked")
    private boolean checkIsEmpty(UserAnswerObject<?> userAnswerObject) {
        if (userAnswerObject.getType().equals(QuestionType.MULTI) ||
                userAnswerObject.getType().equals(QuestionType.SINGLE)){
            var userAnswers = (List<Long>) userAnswerObject.getAnswer();
            return userAnswers.isEmpty();
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
                UserAnswerObject<String> userAnswerObject = new UserAnswerObject<>();
                userAnswerObject.setType(QuestionType.OPEN);
                userAnswerObject.setAnswer(questionDto.getPossibleAnswersDto().get(0).getPossibleAnswer());
                userAnswer.setAnswer(userAnswerObject);
            }
        }
        userAnswerRepository.save(userAnswer);
    }

    private UserAnswerObject<List<Long>> buildSingleUserAnswerObject(long questionId,
                                                                       List<PossibleAnswerDto> possibleAnswerDtoList) {
        UserAnswerObject<List<Long>> userAnswerObject = new UserAnswerObject<>();
        userAnswerObject.setType(QuestionType.SINGLE);
        if (possibleAnswerDtoList.isEmpty()) {
            userAnswerObject.setAnswer(Collections.emptyList());
        } else {
            PossibleAnswer possibleAnswer = possibleAnswerRepository
                    .findAnswerByQuestionIdAndPossibleAnswer(questionId, possibleAnswerDtoList.get(0)
                            .getPossibleAnswer()).orElseThrow();
            userAnswerObject.setAnswer(List.of(possibleAnswer.getId()));
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
            List<String> possibleAnswersNames = possibleAnswerDtoList.stream()
                    .map(PossibleAnswerDto::getPossibleAnswer).collect(Collectors.toList());
            userAnswerObject.setAnswer(answerRepository
                    .findPossibleAnswersIdsByQuestionIdAndByPossibleAnswers(questionId, possibleAnswersNames));
        }
        return userAnswerObject;
    }

    private Question getQuestion(long formId, String questionName) {
        return questionRepository
                .findQuestionByFormIdAndQuestionName(formId, questionName.trim())
                .orElseThrow(() -> new NotFoundException("Question - " +
                        questionName + " for form - " + formId));
    }

    private User findUserByEmail(String userName) {
        return userRepository.findByEmail(userName).orElseThrow(() -> new NotFoundException("User " + userName));
    }

    private Form getForm(long formId) {
        return formsRepository.findById(formId)
                .orElseThrow(() -> new NotFoundException("Form " + formId));
    }
}
