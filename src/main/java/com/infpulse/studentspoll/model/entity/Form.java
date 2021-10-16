package com.infpulse.studentspoll.model.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    @JsonTypeId
    protected Long id;

    @NotEmpty
    @Column(name = "topic_name")
    @Size(max = 255)
    protected String topicName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    protected User owner;

    @Column(name = "last_modified", insertable = false, updatable = false)
    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
    @Setter(AccessLevel.NONE)
    protected LocalDateTime lastModified;

    @Column(name = "created_time", insertable = false, updatable = false)
    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    protected LocalDateTime createdTime;

    @Column(name = "expire_date")
    protected LocalDateTime expireDate;

    @OneToMany
    @ToString.Exclude
    List<UserAnswer> userAnswers = new ArrayList<>();

    @Embedded
    protected Answer answer;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    protected List<Block> listOfBlocks;

    public void addUserAnswer(UserAnswer userAnswer) {
        userAnswers.add(userAnswer);
    }

}