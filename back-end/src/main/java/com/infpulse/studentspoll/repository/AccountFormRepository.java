package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.AccountForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AccountFormRepository extends JpaRepository<AccountForm, Long> {

	Optional<AccountForm> findAccountFormByEmailAndFormIdAndDate(String email, Long id, LocalDateTime date);
}
