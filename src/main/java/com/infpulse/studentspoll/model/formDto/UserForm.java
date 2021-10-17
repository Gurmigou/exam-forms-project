package com.infpulse.studentspoll.model.formDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserForm {

    private String name;
    @Past
    private LocalDateTime passedDate;

    private Long id;
}
