package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.model.securityDto.AuthRequest;
import com.infpulse.studentspoll.model.securityDto.AuthResponse;
import com.infpulse.studentspoll.security.JwtProvider;
import com.infpulse.studentspoll.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final JwtUserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthenticationController(JwtUserDetailsService userDetailsService,
                                    JwtProvider jwtProvider)
    {
        this.userDetailsService = userDetailsService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/api/auth")
    public ResponseEntity<?> createAuthJwtToken(@RequestBody AuthRequest authRequest) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());

            String jwtToken = jwtProvider.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(jwtToken));

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
