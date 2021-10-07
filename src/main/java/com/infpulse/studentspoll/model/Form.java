package com.infpulse.studentspoll.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Form {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    protected Long id;

    protected String topicName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;

    @OneToMany
    List<UserAnswer> userAnswers;
    
    @Embedded
    protected Answer answer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "form")
    protected List<Block> listOfblocks;
    
}