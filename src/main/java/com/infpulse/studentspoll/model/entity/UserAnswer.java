package com.infpulse.studentspoll.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_answer")
public class UserAnswer {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    protected Long id;

    @ManyToOne
    @JoinColumn(updatable = false, name = "answer_owner_id")
    protected User answerOwner;

    @ManyToOne
    @JoinColumn(updatable = false, name = "owner_form_id")
    protected Form belongsToForm;

    @OneToMany
    protected List<Block> listOfUsersBlocks;

    @CreationTimestamp
    @Column(name = "answer_date", nullable = false, insertable = false)
    @Setter(AccessLevel.NONE)
    protected LocalDateTime answerDate;
}
