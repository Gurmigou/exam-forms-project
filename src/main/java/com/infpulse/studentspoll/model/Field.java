package com.infpulse.studentspoll.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "field")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToMany
    private List<Block> listOfParentBlocks;

    private Integer answerValue;

    private String stringValue;

}