package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.PossibleAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PossibleAnswerRepository extends JpaRepository<PossibleAnswer, Long> {
}
