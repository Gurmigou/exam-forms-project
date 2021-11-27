package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.model.securityDto.AuthRequest;
import com.infpulse.studentspoll.model.securityDto.AuthResponse;
import com.infpulse.studentspoll.security.JwtProvider;
import com.infpulse.studentspoll.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AuthenticationController {
    private final JwtUserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationController(JwtUserDetailsService userDetailsService,
                                    JwtProvider jwtProvider,
                                    PasswordEncoder passwordEncoder)
    {
        this.userDetailsService = userDetailsService;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthJwtToken(@RequestBody AuthRequest authRequest) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());

            if (!passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword()))
                throw new BadCredentialsException("");

            String jwtToken = jwtProvider.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(jwtToken));

        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body("Email or password is incorrect.");
        }
    }
}
