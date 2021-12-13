package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.entity.PossibleAnswer;
import com.infpulse.studentspoll.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PossibleAnswerRepository extends JpaRepository<PossibleAnswer, Long> {

    //Should return all possible answers that belong to question
    List<PossibleAnswer> findAllByQuestion(Question question);

    @Query("SELECT pa FROM PossibleAnswer pa " +
            "WHERE pa.question.id = :id AND pa.possibleAnswer = :possibleAnswer")
    Optional<PossibleAnswer> findAnswerByQuestionIdAndPossibleAnswer(@Param("id") Long id,
                                                     @Param("possibleAnswer") String possibleAnswer);

    @Query("SELECT pa FROM PossibleAnswer pa " +
            "WHERE pa.question.id = :questionId AND pa.id = :answerId")
    PossibleAnswer findPossibleAnswerByQuestionIdAndAnswerId(@Param("questionId") long questionId,
                                                             @Param("answerId") long answerId);

    //should find id's of possible answers by question id and possibleAnswer
    @Query("SELECT pa.id FROM PossibleAnswer pa " +
            "WHERE pa.question.id = :qid AND pa.possibleAnswer IN :pNames")
    List<Long> findPossibleAnswersIdsByQuestionIdAndByPossibleAnswers(@Param("qid") long questionId,
                                                                      @Param("pNames") List<String> possibleAnswersNames);
}
