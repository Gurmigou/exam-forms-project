package com.infpulse.studentspoll.model.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Table(name = "form")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    @JsonTypeId
    protected Long id;

    @NotEmpty
    @Column(name = "name")
    @Size(max = 255)
    protected String topicName;

    @Column(name = "max_attempts")
    @Positive
    protected Integer maxAttempts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User formOwner;

    @Future
    @Column(name = "expire_date")
    protected LocalDateTime expireDateTime;

}