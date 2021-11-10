package com.infpulse.studentspoll.security;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter checks if the token is valid
 */
@Component
public class JwtRequestTokenVerifier extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Autowired
    public JwtRequestTokenVerifier(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // execute a "replace" method to remove a "Bearer " word
            final String jwtToken = authorizationHeader.replace("Bearer ", "");

            try {
                String userEmail = jwtProvider.extractUserEmail(jwtToken);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userEmail,
                        null,
                        null
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JwtException e) {
                throw new IllegalStateException(String.format("Token %s cannot be trusted", jwtToken));
            }
        }
        filterChain.doFilter(request, response);
    }
}
