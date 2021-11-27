package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.formDto.userInfo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    // Performs a check for the existence of the current email
    boolean existsUserByEmail(String email);

    @Modifying
    @Query(value = "UPDATE User user SET user.isDeleted = TRUE WHERE user.email = :email")
    void setDeleted(@Param("email") String email);

    @Query(value = "SELECT new com.infpulse.studentspoll.model.formDto.userInfo.UserInfo " +
            "(user.email, user.name, user.surname, user.isDeleted, user.creationTime, user.lastUpdateTime) " +
            "FROM User user WHERE user.email = :email")
    Optional<UserInfo> fetchUserInfo(@Param("email") String email);
}
