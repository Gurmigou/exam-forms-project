package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.entity.UserAnswer;
import com.infpulse.studentspoll.model.formDto.FormHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    /*returns list of form names and id's that user passed*/
    @Query("SELECT new com.infpulse.studentspoll.model.formDto.FormHeader(" +
            "ua.answerOwner.login, " +
            "ua.answerDate, " +
            "ua.belongsToForm.id)" +
            "FROM UserAnswer ua WHERE ua.answerOwner = :user")
    List<FormHeader> getUserAnswers(@Param(value = "user") User user);

    /*should return user's answer where form.id = :formId*/
//	UserAnswer getUserAnswer(long formId, User user);

    UserAnswer findUserAnswerByBelongsToForm_IdAndAnswerOwner_Login(Long belongsToForm_id,
                                                                    String answerOwner_login);
}
