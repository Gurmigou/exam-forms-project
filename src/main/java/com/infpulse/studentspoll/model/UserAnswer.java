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
    protected Long id;

    @ManyToOne
    protected User answerOwner;

    @ManyToOne
    protected Form ownerForm;

    @OneToMany
    protected List<Block> listOfUsersBlocks;
}
