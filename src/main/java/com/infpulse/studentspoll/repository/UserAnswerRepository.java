package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.entity.UserAnswer;
import com.infpulse.studentspoll.model.formDto.UserForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

	/*returns list of form names and id's that user passed*/
	@Query("SELECT new com.infpulse.studentspoll.model.formDto.UserForm(ua.answerOwner.login, ua.dateOfPassage, ua.belongsToForm.id)" +
			"FROM UserAnswer ua WHERE ua.answerOwner = :user")
	List<UserForm> getUserAnswers(User user);

	/*should return user's answer where form.id = :formId*/
	UserAnswer getUserAnswer(long formId, User user);
}
