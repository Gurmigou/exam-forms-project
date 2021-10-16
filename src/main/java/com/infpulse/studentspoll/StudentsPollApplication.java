package com.infpulse.studentspoll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infpulse.studentspoll.model.registrationDto.RegistrationDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentsPollApplication {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(RegistrationDto.builder()
                    .email("gmail@gmail.com")
                    .password("password")
                    .confirmPassword("password")
                    .name("bogdan")
                    .surname("labudiak").build()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        SpringApplication.run(StudentsPollApplication.class, args);
    }
}