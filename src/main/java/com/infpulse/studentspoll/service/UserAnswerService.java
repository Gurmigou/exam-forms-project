package com.infpulse.studentspoll.service;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAnswerService {


}



	    /*public UserAnswer addUserAnswer(Long formId, UserAnswerDto userAnswerDto, String userName) {
        User user = userRepository.findByLogin(userName).orElseThrow(() -> new NotFoundException("User " + userName));
        Form form = formsRepository.findById(formId).orElseThrow(() -> new NotFoundException("Form " + formId));
        UserAnswer userAnswer = UserAnswer.builder()
                .answerOwner(user)
                .listOfUsersBlocks(userAnswerDto.getUsersBlocks())
                .belongsToForm(form)
                .build();
        form.addUserAnswer(userAnswer);
        formsRepository.save(form);
        return userAnswerRepository.save(userAnswer);
    }*/

	/*private Form buildForm(FormDto formDto, User user) {
		return Form.builder()
				.topicName(formDto.getTopicName())
				.owner(user)
				.answer(formDto.getAnswer())
				.expireDate(formDto.getExpireDate())
				.listOfBlocks(formDto.getListOfBlocks())
				.build();
	}*/