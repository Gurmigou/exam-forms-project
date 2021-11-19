package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.db.DBTestSetup;
import com.infpulse.studentspoll.model.entity.PossibleAnswer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PossibleAnswerRepositoryTest extends DBTestSetup {

    protected TestEntityManager testEntityManager;
    protected PossibleAnswerRepositoryTest possibleAnswerRepositoryTest;

    public PossibleAnswerRepositoryTest(TestEntityManager testEntityManager,
                                        PossibleAnswerRepositoryTest possibleAnswerRepositoryTest) {
        this.testEntityManager = testEntityManager;
        this.possibleAnswerRepositoryTest = possibleAnswerRepositoryTest;
    }

//    @BeforeEach
//    public void fillDb() {
//        var question = Questoin
//        var possibleAnswer = PossibleAnswer.builder()
//                .possibleAnswer("testAnswer")
//                .answerValue(1)
//                .isCorrect(true)
//                .
//                .build();
//    }
}