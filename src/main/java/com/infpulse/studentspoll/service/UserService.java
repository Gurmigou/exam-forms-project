package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.exceptions.ConfirmPasswordIsIncorrectException;
import com.infpulse.studentspoll.exceptions.RegistrationException;
import com.infpulse.studentspoll.exceptions.UserAlreadyExistsException;
import com.infpulse.studentspoll.model.User;
import com.infpulse.studentspoll.model.registrationDto.RegistrationDto;
import com.infpulse.studentspoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUser(String login) {
        return userRepository.findByLogin(login);
    }

    public boolean userExists(String login) {
        return userRepository.existsUserByLogin(login);
    }

    public void registerUser(RegistrationDto registrationDto) throws RegistrationException {
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            throw new ConfirmPasswordIsIncorrectException(String.format(
                    "Password and confirm password are not equal: %s != %s",
                    registrationDto.getPassword(),
                    registrationDto.getConfirmPassword()));
        }

        if (userExists(registrationDto.getConfirmPassword())) {
            throw new UserAlreadyExistsException(String.format(
                    "User with email %s already exists", registrationDto.getEmail()));
        }

        User user = mapToUser(registrationDto);
        saveUser(user);
    }

    private User mapToUser(RegistrationDto registrationDto) {
        return User.builder()
                .name(registrationDto.getName())
                .surname(registrationDto.getSurname())
                .login(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .build();
    }
}
