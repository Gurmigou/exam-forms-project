package com.infpulse.studentspoll.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswer {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    private Form ownerForm;

    @OneToMany
    private List<Block> listOfUsersBlocks;
}
