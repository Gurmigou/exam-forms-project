package com.infpulse.studentspoll.model;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.List;

@Embeddable
@Immutable
public class Answer {

    @OneToMany
    private List<Block> listOfBlocks;
}