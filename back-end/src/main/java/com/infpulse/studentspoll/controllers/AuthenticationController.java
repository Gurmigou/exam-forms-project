package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.model.securityDto.LoginRequestDto;
import com.infpulse.studentspoll.model.securityDto.ReloginResponseDto;
import com.infpulse.studentspoll.model.securityDto.TokenTransferDto;
import com.infpulse.studentspoll.security.JwtProvider;
import com.infpulse.studentspoll.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @PostMapping("/login")
    public ResponseEntity<?> loginUsingEmail(@RequestBody LoginRequestDto loginRequest) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

            if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword()))
                throw new BadCredentialsException("");

            String jwtToken = jwtProvider.generateToken(userDetails);

            return ResponseEntity.ok(new TokenTransferDto(jwtToken));

        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body("Email or password is incorrect.");
        }
    }

    @GetMapping("/auth")
    public ResponseEntity<?> authenticateUsingJwtToken(Principal principal) {
        try {
            String jwtToken = jwtProvider.generateToken(principal.getName());
            return ResponseEntity.ok(new ReloginResponseDto(jwtToken, principal.getName(), true));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Jwt token is invalid.");
        }
    }
}
