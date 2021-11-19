package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.exceptions.RegistrationException;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.registrationDto.RegistrationDto;
import com.infpulse.studentspoll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationDto registrationDto) {
        try {
            userService.registerUser(registrationDto);
            return ResponseEntity.ok("You have been successfully registered");
        } catch (RegistrationException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
