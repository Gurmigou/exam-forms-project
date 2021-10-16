package com.infpulse.studentspoll.security;

import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository
                .findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(
                        "User with login %s doesn't exist", login)));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(passwordEncoder.encode(user.getPassword()))
                .authorities(Collections.emptyList())
                .build();
    }
}
