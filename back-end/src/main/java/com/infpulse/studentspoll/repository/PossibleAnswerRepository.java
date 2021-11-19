package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.PossibleAnswer;
import com.infpulse.studentspoll.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PossibleAnswerRepository extends JpaRepository<PossibleAnswer, Long> {

    //Should return all possible answers that belong to question
    List<PossibleAnswer> findAllByQuestion(Question question);

    @Query("SELECT PossibleAnswer FROM PossibleAnswer " +
            "WHERE PossibleAnswer.id = :id AND PossibleAnswer.possibleAnswer = :possibleAnswer")
    PossibleAnswer findAnswerByQuestionIdAndPossibleAnswer(@Param("id") long id,
                                                           @Param("possibleAnswer") String possibleAnswer);

    @Query("SELECT PossibleAnswer FROM PossibleAnswer " +
            "WHERE PossibleAnswer.question.id = :questionId AND PossibleAnswer.id = :answerId")
    PossibleAnswer findPossibleAnswerByQuestionIdAndAnswerId(@Param("questionId") long questionId,
                                                             @Param("answerId") long answerId);

    //should find id's of possible answers by question id and possibleAnswer
    @Query("SELECT PossibleAnswer.id FROM PossibleAnswer " +
            "WHERE PossibleAnswer.id = :qid AND PossibleAnswer.possibleAnswer IN :pNames")
    List<Long> findPossibleAnswersIdsByQuestionIdAndByPossibleAnswers(@Param("qid") long questionId,
                                                                      @Param("pNames") List<String> possibleAnswersNames);
}
