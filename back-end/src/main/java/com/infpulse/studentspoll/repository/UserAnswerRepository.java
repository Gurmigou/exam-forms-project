package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.PossibleAnswer;
import com.infpulse.studentspoll.model.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

	PossibleAnswer findAnswerByQuestionIdAndPossibleAnswer(long id, String possibleAnswer);

	//should find user's answer by AccountForm id and parent question id
	UserAnswer findAnswerByAccountFormIdAndParentQuestionId(long accountFormId, long questionId);

	PossibleAnswer findPossibleAnswerByQuestionIdAndAnswerId(long questionId, long answerId);
}
