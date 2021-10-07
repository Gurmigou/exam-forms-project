package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.model.User;
import com.infpulse.studentspoll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public User saveUser(User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/user")
    public User getUser(String login) {
        return userService.getUser(login).orElse()
    }

}
