package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.PossibleAnswer;
import com.infpulse.studentspoll.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PossibleAnswerRepository extends JpaRepository<PossibleAnswer, Long> {

	//Should return all possible answers that belong to question
	List<PossibleAnswer> findAllByQuestion(Question question);
}
