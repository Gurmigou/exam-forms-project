package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.model.formDto.userInfo.EditUserDto;
import com.infpulse.studentspoll.model.formDto.userInfo.UserInfo;
import com.infpulse.studentspoll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(Principal principal) {
        try {
            UserInfo userInfo = userService.getUserInfo(principal.getName());
            return ResponseEntity.ok(userInfo);
        } catch (Throwable t) {
            return ResponseEntity
                    .badRequest()
                    .body(t.getMessage());
        }
    }

    @PutMapping("/user")
    public ResponseEntity<?> editUser(Principal principal, @RequestBody @Valid EditUserDto editUser) {
        try {
            UserInfo updatedUser = userService.updateUser(principal.getName(), editUser);
            return ResponseEntity.ok(updatedUser);
        } catch (Throwable t) {
            return ResponseEntity
                    .badRequest()
                    .body(t.getMessage());
        }
    }
}