package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAnswerController {

	private final UserAnswerService userAnswerService;

	@Autowired
	public UserAnswerController(UserAnswerService userAnswerService) {
		this.userAnswerService = userAnswerService;
	}



}
