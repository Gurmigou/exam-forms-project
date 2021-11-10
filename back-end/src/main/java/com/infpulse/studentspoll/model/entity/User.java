package com.infpulse.studentspoll.model.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    @JsonTypeId
    protected Long id;

    protected String name;

    protected String surname;
    @Column(name = "email", unique = true)
    protected String email;

    protected String password;

    @Column(name = "is_deleted", nullable = false)
    protected Boolean isDeleted;

    @CreationTimestamp
    @Column(name = "creation_time")
    protected LocalDateTime creationTime;

    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
    @Column(name = "last_updated_time")
    protected LocalDateTime lastUpdateTime;

}
