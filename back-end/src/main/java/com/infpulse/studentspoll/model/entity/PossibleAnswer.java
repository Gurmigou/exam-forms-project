package com.infpulse.studentspoll.model.entity;

import lombok.*;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Table(name = "possible_answer")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Immutable
public class PossibleAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    protected Long id;

    @Column(name = "answer", nullable = false)
    protected String possibleAnswer;

    @Column(name = "answer_score", nullable = false)
    @PositiveOrZero
    protected Integer answerValue;

    @Column(name = "is_correct", nullable = false, columnDefinition = "boolean default false")
    protected Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    protected Question question;
}