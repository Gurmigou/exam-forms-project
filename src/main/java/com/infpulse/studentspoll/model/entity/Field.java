package com.infpulse.studentspoll.model.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Table(name = "field")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    protected Long id;

    @ManyToMany(mappedBy = "listOfFields")
    protected List<Block> listOfParentBlocks;

    @Positive
    @Column(name = "answer_value")
    protected Integer answerValue;
    @NotEmpty
    @Column(name = "variant")
    protected String variant;

}