package com.infpulse.studentspoll.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infpulse.studentspoll.model.entity.AccountForm;
import com.infpulse.studentspoll.model.formDto.passedForm.PassedFormDto;
import com.infpulse.studentspoll.model.formDto.submitForm.SubmitAnswerDto;
import com.infpulse.studentspoll.service.EmailService;
import com.infpulse.studentspoll.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserAnswerController {

    private final UserAnswerService userAnswerService;
    private final EmailService emailService;

    @Autowired
    public UserAnswerController(UserAnswerService userAnswerService, EmailService emailService) {
        this.userAnswerService = userAnswerService;
        this.emailService = emailService;
    }

    /**
     * Saves the answers of the form that the user has passed (when clicking on ex. SUBMIT FORM)
     */
    @PostMapping("/answers/new")
    public ResponseEntity<?> saveAnswer(@RequestBody @Valid SubmitAnswerDto submitAnswerDto, Principal principal) {
        AccountForm accountForm = userAnswerService.submitAnswer(submitAnswerDto, principal.getName());
        try {
            emailService.sendFormResult(accountForm);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * @param formId the id of the form
     * @param date   the date when the form was passed
     * @return a result (analytics) of the form that the user with email {@code principal.getName()} has passed
     */
    @GetMapping("/answers/{formId}/{date}")
    public ResponseEntity<?> getAnswer(@PathVariable long formId,
                                       @PathVariable
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                       @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
                                               LocalDateTime date,
                                       Principal principal) {
        PassedFormDto passedFormDto = userAnswerService.getAnswer(formId, principal.getName(), date);
        if (Objects.nonNull(passedFormDto)) {
            return ResponseEntity.ok(passedFormDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
