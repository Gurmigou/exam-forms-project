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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
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

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void test() {
        System.out.println("PRINTED:" + entityManager);
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
                .attempts(120)
                .formState(FormState.CREATED)
                .expireDateTime(LocalDateTime.now().plusWeeks(1))
                .build();
        Form secondForm = Form.builder()
                .owner(owner)
                .topicName("testTopic2")
                .attempts(120)
                .formState(FormState.CREATED)
                .expireDateTime(LocalDateTime.now().plusWeeks(1))
                .build();
        AccountForm accountForm = AccountForm.builder()
                .form(form)
                .resultScore(123)
                .user(owner)
                .build();
        AccountForm accountForm1 = AccountForm.builder()
                .form(form)
                .resultScore(100)
                .user(owner)
                .build();
        AccountForm accountForm3 = AccountForm.builder()
                .form(secondForm)
                .resultScore(123)
                .user(owner)
                .build();
        AccountForm accountForm4 = AccountForm.builder()
                .form(secondForm)
                .resultScore(100)
                .user(owner)
                .build();

        testEntityManager.persist(owner);
        testEntityManager.persist(form);
        testEntityManager.persist(secondForm);
        testEntityManager.persist(accountForm);
        testEntityManager.persist(accountForm1);
        testEntityManager.persist(accountForm3);
        testEntityManager.persist(accountForm4);

        List<OwnedFormHeader> testList = formRepository.getOwnedFormHeaders(owner.getEmail());

        assertThat(testList).isNotEmpty();
    }
}