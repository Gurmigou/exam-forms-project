package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.repository.FormRepository;
import com.infpulse.studentspoll.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class JasperReportService {
    private final UserRepository userRepository;
    private final FormRepository formRepository;

    public JasperReportService(UserRepository userRepository, FormRepository formRepository) {
        this.userRepository = userRepository;
        this.formRepository = formRepository;
    }

}
