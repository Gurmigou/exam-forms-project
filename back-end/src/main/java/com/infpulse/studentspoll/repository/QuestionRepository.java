package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    //Should return all questions that belong to form
    @Query("SELECT q FROM Question q " +
            "WHERE q.ownerForm.id = :formId")
    List<Question> findAllByOwnerForm(@Param("formId") long formId);

    //should find question by owner form id and question name
    @Query("SELECT q FROM Question q " +
            "WHERE q.ownerForm.id = :qId AND q.questionName = :qName")
    Optional<Question> findQuestionByFormIdAndQuestionName(@Param("qId") Long id,
                                                           @Param("qName") String questionName);
}
