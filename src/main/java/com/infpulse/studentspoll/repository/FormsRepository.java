package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormsRepository extends JpaRepository<Form, Long> {
}
