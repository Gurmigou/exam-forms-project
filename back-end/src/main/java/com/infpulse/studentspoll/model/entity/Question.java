package com.infpulse.studentspoll.model.entity;

import com.infpulse.studentspoll.model.state.QuestionType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table(name = "question")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    protected Long id;

    @NotBlank
    @Column(name = "question_name", nullable = false, length = 2000)
    protected String questionName;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_type", nullable = false)
    protected QuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "form_id")
    protected Form ownerForm;

}