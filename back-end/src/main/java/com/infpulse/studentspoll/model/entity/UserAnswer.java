package com.infpulse.studentspoll.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user_answer")
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    protected Question parentQuestion;

    @Column(name = "answer", length = 2500, nullable = false)
    protected UserAnswerObject answer;

    @ManyToOne
    @JoinColumn(name = "account_forms_id")
    protected AccountForm accountForm;
}