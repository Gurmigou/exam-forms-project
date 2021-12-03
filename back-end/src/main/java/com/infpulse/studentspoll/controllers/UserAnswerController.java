package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.model.entity.AccountForm;
import com.infpulse.studentspoll.model.formDto.passedForm.PassedFormDto;
import com.infpulse.studentspoll.model.formDto.submitForm.SubmitAnswerDto;
import com.infpulse.studentspoll.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserAnswerController {

	private final UserAnswerService userAnswerService;

	@Autowired
	public UserAnswerController(UserAnswerService userAnswerService) {
		this.userAnswerService = userAnswerService;
	}


	@PostMapping("/answers/new")
	public ResponseEntity<?> saveAnswer(@RequestBody @Valid SubmitAnswerDto submitAnswerDto, Principal principal) {
		AccountForm accountForm = userAnswerService.submitAnswer(submitAnswerDto, principal.getName());
		if (Objects.nonNull(accountForm)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/answers/{formId}/{userEmail}/{date}")
	public ResponseEntity<?> getAnswer(@PathVariable long formId, @PathVariable String userEmail, @PathVariable LocalDateTime date) {
		PassedFormDto passedFormDto = userAnswerService.getAnswer(formId, userEmail, date);
		if (Objects.nonNull(passedFormDto)) {
			return ResponseEntity.ok(passedFormDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}