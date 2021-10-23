package com.infpulse.studentspoll;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentsPollApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Module dateTimeModule() {
        return new JavaTimeModule();
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentsPollApplication.class, args);
    }
}