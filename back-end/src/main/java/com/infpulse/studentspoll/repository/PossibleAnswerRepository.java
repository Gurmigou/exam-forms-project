package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.PossibleAnswer;
import com.infpulse.studentspoll.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PossibleAnswerRepository extends JpaRepository<PossibleAnswer, Long> {

	//Should return all possible answers that belong to question
	List<PossibleAnswer> findAllByQuestion(Question question);

	//should find id's of possible answers by question id and possibleAnswer
	List<Long> findPossibleAnswersIdsByQuestionIdAndPossibleAnswers(long questionId, List<String> possibleAnswersNames);
}
