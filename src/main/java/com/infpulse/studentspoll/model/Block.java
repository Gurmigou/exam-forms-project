package com.infpulse.studentspoll.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "block")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Access(AccessType.PROPERTY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "block_list_of_fields",
            joinColumns = @JoinColumn(name = "block_id",
                    referencedColumnName = "list_of_fields_id"))
    private List<Field> listOfFields;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

}