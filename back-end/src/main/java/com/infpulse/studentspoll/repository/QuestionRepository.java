package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	//Should return all questions that belong to form
	List<Question> findAllByOwnerForm(long formId);

	//should find question by owner form id and question name
	Optional<Question> findQuestionByFormIdAndQuestionName(Long id, String questionName);
}
