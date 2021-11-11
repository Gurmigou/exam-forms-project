package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.PossibleAnswer;
import com.infpulse.studentspoll.model.entity.UserAnswer;
import com.infpulse.studentspoll.model.formDto.passedForm.PossibleAnswerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

	//should find all user's answers by email and date
	List<UserAnswer> findAnswersByEmailAndDate(String email, LocalDateTime date);

	PossibleAnswer findAnswerByQuestionIdAndPossibleAnswer(Long id, PossibleAnswerDto possibleAnswerDto);
}
