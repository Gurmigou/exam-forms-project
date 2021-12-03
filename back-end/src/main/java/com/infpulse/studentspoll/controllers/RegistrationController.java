package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.exceptions.RegistrationException;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.securityDto.RegistrationDto;
import com.infpulse.studentspoll.service.EmailService;
import com.infpulse.studentspoll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class RegistrationController {
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public RegistrationController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationDto registrationDto) {
        try {
            User newUser = userService.registerUser(registrationDto);
            emailService.sendWelcomeEmail(newUser);
            return ResponseEntity.ok("You have been successfully registered");
        } catch (RegistrationException | MessagingException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
