package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.AccountForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountFormRepository extends JpaRepository<AccountForm, Long> {

}
