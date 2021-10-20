package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormsRepository extends JpaRepository<Form, Long> {

    /*should return all forms where owner equals user*/
    List<Form> findAllByOwner(User user);

    @Query(value = "UPDATE Form f SET f.isDeleted = TRUE WHERE f.id = :id")
    @Modifying
    void setDeleted(@Param(value = "id") long id);
}