package com.infpulse.studentspoll.model.formDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class UserForm {

    private String name;
    @Past
    private LocalDateTime passedDate;

    private Long id;
}
