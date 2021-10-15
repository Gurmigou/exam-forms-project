package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    boolean existsUserByLogin(String login);
}
