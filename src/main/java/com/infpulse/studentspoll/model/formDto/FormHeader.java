package com.infpulse.studentspoll.model.formDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class FormHeader {

    @NotBlank
    private String name;

    @Past
    private LocalDateTime passedDate;

    @Positive
    private Long id;
}
