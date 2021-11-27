package com.infpulse.studentspoll.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This security configuration file is used only while testing.
 */
@Configuration
@EnableWebSecurity
@Profile("test")
public class NoAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .addFilterBefore(new TestProfileJwtRequestTokenVerifier(), UsernamePasswordAuthenticationFilter.class);
    }
}

class TestProfileJwtRequestTokenVerifier extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "test_name@somemail.com",
                null,
                null
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}