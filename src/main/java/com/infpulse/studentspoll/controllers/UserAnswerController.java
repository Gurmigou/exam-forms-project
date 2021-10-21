package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.model.formDto.UserAnswerDto;
import com.infpulse.studentspoll.model.formDto.FormHeader;
import com.infpulse.studentspoll.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAnswerController {

	private final UserAnswerService userAnswerService;

	@Autowired
	public UserAnswerController(UserAnswerService userAnswerService) {
		this.userAnswerService = userAnswerService;
	}

	@PostMapping("/forms/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public UserAnswerDto postUserAnswer(@PathVariable long id, @RequestBody UserAnswerDto userAnswerDto,
	                                    Principal principal) {
		return userAnswerService.saveAnswer(id, userAnswerDto, principal.getName());
	}

	@GetMapping("/form-view/{id}/{username}")
	public UserAnswerDto getUserAnswer(@PathVariable long id,
	                                   @PathVariable(value = "username", required = false) String userName,
	                                   Principal principal) {
		if (userName != null && !userName.isEmpty()) {
			return userAnswerService.getUserAnswer(id, userName);
		} else {
			return userAnswerService.getUserAnswer(id, principal.getName());
		}
	}

	@GetMapping("/forms-answers")
	public List<FormHeader> getUserAnswers(Principal principal) {
		return userAnswerService.getUserAnswers(principal.getName());
	}


}
