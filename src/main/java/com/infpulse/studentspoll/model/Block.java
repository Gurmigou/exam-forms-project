package com.infpulse.studentspoll.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Table(name = "block")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    protected Long id;

    @NotEmpty
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "blocks_fields",
            joinColumns = {
                    @JoinColumn(name = "block_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "field_id")
            }
    )
    protected List<Field> listOfFields;

    protected String questionName;
}