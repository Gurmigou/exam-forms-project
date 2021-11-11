package com.infpulse.studentspoll.model.formDto.submitForm;

import com.infpulse.studentspoll.model.formDto.passedForm.QuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SubmitAnswerDto {
    private Long formId;
    private List<QuestionDto> questionDtoList;
}
