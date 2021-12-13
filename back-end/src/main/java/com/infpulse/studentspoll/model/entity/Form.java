package com.infpulse.studentspoll.model.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import com.infpulse.studentspoll.model.state.FormState;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
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
    @Column(name = "name", length = 2000)
    protected String topicName;

    @Column(name = "max_attempts")
    @Positive
    protected Integer attempts;

    @ManyToOne
    @JoinColumn(name = "account_id")
    protected User owner;

    @Future
    @Column(name = "expire_date")
    protected LocalDateTime expireDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    protected FormState formState;

    @Positive
    @Column(name = "max_result")
    protected Integer formMaxResult;
}