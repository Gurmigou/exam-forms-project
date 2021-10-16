package com.infpulse.studentspoll.model.entity;

import lombok.*;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import javax.validation.constraints.Max;
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
    protected Integer answerValue;
    @NotEmpty
    protected String stringValue;
}