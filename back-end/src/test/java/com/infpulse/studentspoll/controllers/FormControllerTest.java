package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.service.FormService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

class FormControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FormService formService;

    @Mock
    private Principal principal;

    @InjectMocks
    private FormController formController;



}