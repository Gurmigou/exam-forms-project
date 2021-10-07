package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.model.User;
import com.infpulse.studentspoll.repository.UserRepository;

import java.util.Optional;

public class UserService {
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUser(String login) {
        return userRepository.findByLogin(login);
    }
}
