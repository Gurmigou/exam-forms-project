package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.User;
import org.springframework.context.annotation.Profile;
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
    void setDeleted(@Param(value = "email") String email);

//    @Query(value = "SELECT email AS email, name AS name, surname AS surname," +
//            "isDeleted AS isDeleted, creationTime AS creationTime, lasLoginTime AS lasLoginTime " +
//            "FROM User user WHERE user.email = :email")
//    Optional<UserInfo> fetchUserInfo(@Param(value = "email") String email);

}
