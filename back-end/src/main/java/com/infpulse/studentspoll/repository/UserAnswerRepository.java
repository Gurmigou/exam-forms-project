package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

	//should find user's answer by AccountForm id and parent question id
	UserAnswer findAnswerByAccountFormIdAndParentQuestionId(long accountFormId, long questionId);
}
