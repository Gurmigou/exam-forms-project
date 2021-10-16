package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.entity.UserAnswer;
import com.infpulse.studentspoll.model.formDto.FormDto;
import com.infpulse.studentspoll.model.formDto.UserAnswerDto;
import com.infpulse.studentspoll.repository.FormsRepository;
import com.infpulse.studentspoll.repository.UserAnswerRepository;
import com.infpulse.studentspoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    private final FormsRepository formsRepository;
    private final UserRepository userRepository;
    private final UserAnswerRepository userAnswerRepository;

    @Autowired
    public FormService(FormsRepository formsRepository, UserRepository userRepository,
                       UserAnswerRepository userAnswerRepository) {
        this.formsRepository = formsRepository;
        this.userRepository = userRepository;
        this.userAnswerRepository = userAnswerRepository;
    }

    public UserAnswer addUserAnswer(Long formId, UserAnswerDto userAnswerDto, String userName) {
        User user = userRepository.findByLogin(userName).orElseThrow();
        Form form = formsRepository.findById(formId).orElseThrow();
        UserAnswer userAnswer = UserAnswer.builder()
                .answerOwner(user)
                .listOfUsersBlocks(userAnswerDto.getUsersBlocks())
                .belongsToForm(form)
                .build();
        form.addUserAnswer(userAnswer);
        formsRepository.save(form);
        return userAnswerRepository.save(userAnswer);
    }

    public Form saveForm(FormDto formDto, String userName) {
        User user = userRepository.findByLogin(userName).orElseThrow();
        Form form = buildForm(formDto, user);
        return formsRepository.save(form);
    }

    private Form buildForm(FormDto formDto, User user) {
        return Form.builder()
                .topicName(formDto.getTopicName())
                .owner(user)
                .answer(formDto.getAnswer())
                .expireDate(formDto.getExpireDate())
                .listOfBlocks(formDto.getListOfBlocks())
                .build();
    }
}
