package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.exceptions.ConfirmPasswordIsIncorrectException;
import com.infpulse.studentspoll.exceptions.RegistrationException;
import com.infpulse.studentspoll.exceptions.UserAlreadyExistsException;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.formDto.userInfo.EditUserDto;
import com.infpulse.studentspoll.model.formDto.userInfo.UserInfo;
import com.infpulse.studentspoll.model.securityDto.RegistrationDto;
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

    public Optional<User> findUser(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean userExists(String email) {
        return userRepository.existsUserByEmail(email);
    }

    public UserInfo updateUser(String email, EditUserDto editUser) throws ConfirmPasswordIsIncorrectException {
        User user = findUser(email).orElseThrow(() -> new IllegalStateException(String.format(
                "User with email %s doesn't exist.", email)));

        if (editUser.getOldPassword() != null && !passwordEncoder.matches(editUser.getOldPassword(), user.getPassword())) {
            throw new ConfirmPasswordIsIncorrectException("Entered password is incorrect.");
        }

        if (editUser.getNewName() != null)
            user.setName(editUser.getNewName());
        if (editUser.getNewSurname() != null)
            user.setSurname(editUser.getNewSurname());
        if (editUser.getNewPassword() != null)
            user.setPassword(passwordEncoder.encode(editUser.getNewPassword()));

        User updatedUser = saveUser(user);
        return mapToUserInfo(updatedUser);
    }

    public UserInfo getUserInfo(String email) {
        return userRepository.fetchUserInfo(email).orElseThrow(() -> new IllegalStateException(String.format(
                "User with email %s doesn't exist", email)));
    }

    public User registerUser(RegistrationDto registrationDto) throws RegistrationException {
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            throw new ConfirmPasswordIsIncorrectException(String.format(
                    "Password and confirm password are not equal: %s != %s",
                    registrationDto.getPassword(),
                    registrationDto.getConfirmPassword()));
        }

        if (userExists(registrationDto.getEmail())) {
            throw new UserAlreadyExistsException(String.format(
                    "User with email %s already exists", registrationDto.getEmail()));
        }

        User user = mapToUser(registrationDto);
        return saveUser(user);
    }

    private User mapToUser(RegistrationDto registrationDto) {
        return User.builder()
                .name(registrationDto.getName())
                .surname(registrationDto.getSurname())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .isDeleted(false)
                .build();
    }

    private UserInfo mapToUserInfo(User user) {
        return UserInfo.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }
}
