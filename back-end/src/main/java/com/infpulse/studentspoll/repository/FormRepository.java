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

    @Query(value = "SELECT new com.infpulse.studentspoll.model.formDto.formHeaders.OwnedFormHeader " +
            "(f.id, f.topicName, f.expireDateTime, f.formState) " +
            "FROM Form f " +
            "WHERE f.owner.email = :email " +
            "AND f.formState != 'DELETED'")
    List<OwnedFormHeader> getOwnedFormHeaders(@Param("email") String email);

    @Query(value = "SELECT new com.infpulse.studentspoll.model.formDto.formHeaders.AvailableFormHeader " +
            "(f.id, f.topicName, a.resultScore, a.answerDate, f.formMaxResult) " +
            "FROM Form f INNER JOIN AccountForm a " +
            "ON f.id = a.form.id " +
            "WHERE a.user.email = :email")
    List<AvailableFormHeader> getPassedFormHeader(@Param("email") String email);

    @Query(value = "SELECT new com.infpulse.studentspoll.model.formDto.formHeaders.UserFormValuation " +
            "(af.user.name, af.user.surname, af.answerDate, af.resultScore, f.formMaxResult) " +
            "FROM AccountForm af INNER JOIN Form f " +
            "ON f.id = af.form.id " +
            "WHERE af.form.owner.email = :formOwnerEmail AND f.id = :formId")
    List<UserFormValuation> getUserFormValuationList(@Param("formOwnerEmail") String email,
                                                     @Param("formId") long id);
}
