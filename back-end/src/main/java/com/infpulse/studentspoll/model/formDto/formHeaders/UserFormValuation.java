package com.infpulse.studentspoll.model.formDto.formHeaders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserFormValuation {
    @NotBlank
    private String userName;
    @NotBlank
    private String surname;
    @Past
    private LocalDateTime answerDate;
    @PositiveOrZero
    private Integer resultScore;
    @Positive
    private Integer formMaxResult;
}
