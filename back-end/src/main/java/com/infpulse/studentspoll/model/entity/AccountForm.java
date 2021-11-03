package com.infpulse.studentspoll.model.entity;

import com.infpulse.studentspoll.model.state.State;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "account_form")
public class AccountForm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    protected User user;

    @ManyToOne
    @JoinColumn(name = "form_id")
    protected Form form;

    @Column(name = "result_score")
    protected Integer resultScore;

    @Column(name = "answer_date")
    @CreationTimestamp
    protected LocalDateTime answerDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    protected State state;
}
