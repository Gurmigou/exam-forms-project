package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.repository.UserAnswerRepository;
import com.infpulse.studentspoll.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserAnswerServiceTest {

	@MockBean
	protected UserAnswerRepository answerRepository;
	@MockBean
	protected UserRepository userRepository;
	@MockBean
	protected FormService formService;

	@InjectMocks
	UserAnswerService answerService;


}
