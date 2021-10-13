package com.infpulse.studentspoll.security;

import com.infpulse.studentspoll.model.User;
import com.infpulse.studentspoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository
                .findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with login %s doesn't exist", login)));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword()) // TODO: 13.10.2021 нужно ли хэшировать?
                .build();
    }
}
