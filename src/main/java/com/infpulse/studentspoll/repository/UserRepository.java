package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    boolean existsUserByLogin(String login);

    @Query(value = "UPDATE User us SET us.isDeleted = TRUE WHERE us.login = :login")
    @Modifying
    void setDelted(@Param(value = "login") String login);

}
