package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.formDto.formHeaders.AvailableFormHeader;
import com.infpulse.studentspoll.model.formDto.formHeaders.OwnedFormHeader;
import com.infpulse.studentspoll.model.formDto.formHeaders.UserFormValuation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

    @Query(value = "SELECT f.id AS id, f.topicName AS topicName, " +
            "f.expireDateTime AS expireDate, a.formState AS state " +
            "FROM Form f INNER JOIN AccountForm a " +
            "ON f.id = a.form.id " +
            "WHERE f.owner.email = :email")
    List<OwnedFormHeader> getOwnedFormHeaders(@Param("email") String email);

    @Query(value = "SELECT f.id AS id, f.topicName AS topicName, " +
            "a.resultScore AS formScore, a.answerDate AS answerDate " +
            "FROM Form f INNER JOIN AccountForm a " +
            "ON f.id = a.form.id " +
            "WHERE a.user.email = :email")
    List<AvailableFormHeader> getPassedFormHeader(@Param("email") String email);

    @Query(value = "SELECT af.user.name, af.user.surname, af.answerDate, af.resultScore " +
            "FROM AccountForm af " +
            "WHERE af.form.owner.email = :formOwnerEmail")
    List<UserFormValuation> getUserFormValuationList(@Param("formOwnerEmail") String email);

}
