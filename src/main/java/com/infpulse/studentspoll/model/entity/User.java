package com.infpulse.studentspoll.model.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
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

    @Column(name = "is_deleted", columnDefinition = "boolean default false", nullable = false)
    protected Boolean isDeleted;

    @CreationTimestamp
    @Column(name = "creation_time")
    protected LocalDateTime creationTime;

    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
    @Column(name = "last_updated_time")
    protected LocalDateTime lastUpdateTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @ToString.Exclude
    protected List<Form> ownedForms;

    @OneToMany
    @ToString.Exclude
    protected List<Form> completedForms;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "answerOwner")
    @ToString.Exclude
    protected List<UserAnswer> userAnswers;

    public void addOwnedForm(Form form) {
        form.setOwner(this);
        ownedForms.add(form);
    }
}
