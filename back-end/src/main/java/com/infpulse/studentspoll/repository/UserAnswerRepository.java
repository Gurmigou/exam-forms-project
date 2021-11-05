package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
}
