package com.infpulse.studentspoll.model.formDto.passedForm;

import com.infpulse.studentspoll.model.state.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class QuestionDto {
    @NotBlank
    @Max(2000)
    private String questionName;

    @NotNull
    private QuestionType questionType;

    @NotEmpty
    @NotNull
    private List<PossibleAnswerDto> possibleAnswersDto;
}
