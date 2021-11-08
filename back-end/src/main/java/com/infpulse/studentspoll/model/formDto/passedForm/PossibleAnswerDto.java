package com.infpulse.studentspoll.model.formDto.passedForm;

import com.infpulse.studentspoll.model.state.AnswerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PossibleAnswerDto {
    @NotBlank
    @Max(255)
    private String possibleAnswer;
    @NotNull
    private AnswerStatus answerStatus;
    @PositiveOrZero
    private Integer answerValue;
}
