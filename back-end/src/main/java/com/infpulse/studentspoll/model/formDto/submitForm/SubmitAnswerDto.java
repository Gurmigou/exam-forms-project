package com.infpulse.studentspoll.model.formDto.submitForm;

import com.infpulse.studentspoll.model.formDto.passedForm.QuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SubmitAnswerDto {
    @Positive
    private Long formId;
    @NotEmpty
    private List<QuestionDto> questionDtoList;
}
