package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.db.DBTestSetup;
import com.infpulse.studentspoll.model.entity.AccountForm;
import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.formDto.formHeaders.OwnedFormHeader;
import com.infpulse.studentspoll.model.state.FormState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FormRepositoryTest extends DBTestSetup {

    protected FormRepository formRepository;
    protected TestEntityManager testEntityManager;

    @Autowired
    public FormRepositoryTest(FormRepository formRepository, TestEntityManager testEntityManager) {
        this.formRepository = formRepository;
        this.testEntityManager = testEntityManager;
    }

    @Test
    public void itShouldReturnOwnedFormHeaders() {
        User owner = User.builder()
                .email("testEmail")
                .isDeleted(false)
                .name("testName")
                .surname("testSurname")
                .password("password")
                .build();
        Form form = Form.builder()
                .owner(owner)
                .topicName("testTopic")
                .maxAttempts(120)
                .expireDateTime(new Timestamp(System.currentTimeMillis()))
                .build();
        Form secondForm = Form.builder()
                .owner(owner)
                .topicName("testTopic2")
                .maxAttempts(120)
                .expireDateTime(new Timestamp(System.currentTimeMillis()))
                .build();
        AccountForm accountForm = AccountForm.builder()
                .form(form)
                .resultScore(123)
                .formState(FormState.PASSED)
                .user(owner)
                .build();
        AccountForm accountForm1 = AccountForm.builder()
                .form(form)
                .resultScore(100)
                .formState(FormState.PASSED)
                .user(owner)
                .build();
        AccountForm accountForm3 = AccountForm.builder()
                .form(secondForm)
                .resultScore(123)
                .formState(FormState.PASSED)
                .user(owner)
                .build();
        AccountForm accountForm4 = AccountForm.builder()
                .form(secondForm)
                .resultScore(100)
                .formState(FormState.PASSED)
                .user(owner)
                .build();

        testEntityManager.persist(owner);
        testEntityManager.persist(form);
        testEntityManager.persist(secondForm);
        testEntityManager.persist(accountForm);
        testEntityManager.persist(accountForm1);
        testEntityManager.persist(accountForm3);
        testEntityManager.persist(accountForm4);

        List<OwnedFormHeader> testList = formRepository.getOwnedFormHeaders(owner);

        assertThat(testList).isNotEmpty();
    }
}