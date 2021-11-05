package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.Question;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
