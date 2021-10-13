package com.infpulse.studentspoll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infpulse.studentspoll.model.Form;
import com.infpulse.studentspoll.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class StudentsPollApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentsPollApplication.class, args);
    }

}
