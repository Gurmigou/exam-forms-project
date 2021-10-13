package com.infpulse.studentspoll.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    @JsonTypeId
    protected Long id;

    protected String name;

    protected String surname;

    protected String login;

    protected String password;

    @CreationTimestamp
    protected LocalDateTime creationTime;

    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
    protected LocalDateTime lastUpdateTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @ToString.Exclude
    protected List<Form> ownedForms;

    @OneToMany
    @ToString.Exclude
    protected List<Form> comletedForms;

    public void addOwnedForm(Form form) {
        form.setOwner(this);
        ownedForms.add(form);
    }
}
