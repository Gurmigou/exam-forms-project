package com.infpulse.studentspoll.model;

import lombok.*;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
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

    protected Integer answerValue;

    protected String stringValue;
}