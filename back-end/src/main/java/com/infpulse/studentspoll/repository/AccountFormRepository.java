package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.AccountForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AccountFormRepository extends JpaRepository<AccountForm, Long> {

    @Query("SELECT ac FROM AccountForm ac " +
            "WHERE ac.form.id = :id AND ac.user.email = :email AND ac.answerDate = :date")
    Optional<AccountForm> findAccountFormByEmailAndFormIdAndDate(@Param("email") String email,
                                                                 @Param("id") Long id,
                                                                 @Param("date") LocalDateTime date);

    @Query("SELECT COUNT(af.form.id) < af.form.attempts " +
            "FROM AccountForm af " +
            "WHERE af.form.id = :formId AND af.user.email = :userName " +
            "GROUP BY af.form.attempts")
    boolean checkIfEnoughAttempts(@Param("formId") long formId,
                                  @Param("userName") String userName);
}
