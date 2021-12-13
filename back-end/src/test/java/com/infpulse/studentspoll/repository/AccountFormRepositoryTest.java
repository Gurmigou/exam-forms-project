package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.db.DBTestSetup;
import com.infpulse.studentspoll.model.entity.AccountForm;
import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.state.FormState;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountFormRepositoryTest extends DBTestSetup {
    protected TestEntityManager testEntityManager;
    protected AccountFormRepository accountFormRepository;

    public AccountFormRepositoryTest(TestEntityManager testEntityManager,
                                     AccountFormRepository accountFormRepository) {
        this.testEntityManager = testEntityManager;
        this.accountFormRepository = accountFormRepository;
    }

    @Test
    public void itShouldReturnAccountForm() {
        User owner = User.builder()
                .name("ownerName")
                .surname("ownerSurname")
                .email("ownerEmail")
                .password("ownerPassword")
                .isDeleted(false)
                .build();

        User secondOwner = User.builder()
                .name("secondOwnerName")
                .surname("secondOwnerSurname")
                .email("secondOwnerEmail")
                .password("secondOwnerPassword")
                .isDeleted(false)
                .build();

        Form form = Form.builder()
                .topicName("testTopic")
                .owner(owner)
                .expireDateTime(LocalDateTime.now().plusWeeks(1))
                .formState(FormState.PASSED)
                .attempts(99)
                .build();

        Form secondForm = Form.builder()
                .topicName("testTopic2")
                .owner(owner)
                .expireDateTime(LocalDateTime.now().plusWeeks(1))
                .formState(FormState.PASSED)
                .attempts(99)
                .build();

        AccountForm accountForm = AccountForm.builder()
                .form(form)
                .user(owner)
                .resultScore(25)
                .build();

        AccountForm accountForm4 = AccountForm.builder()
                .form(secondForm)
                .user(owner)
                .resultScore(30)
                .build();

        AccountForm accountForm2 = AccountForm.builder()
                .form(secondForm)
                .user(owner)
                .resultScore(30)
                .build();

        AccountForm accountForm3 = AccountForm.builder()
                .form(secondForm)
                .user(secondOwner)
                .resultScore(30)
                .build();

    }
}