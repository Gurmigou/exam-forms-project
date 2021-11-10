package com.infpulse.studentspoll.model.formDto.submitForm;

import com.infpulse.studentspoll.model.formDto.passedForm.QuestionDto;

import java.util.List;

public class SubmitAnswerDto {
    private Long formId;
    private List<QuestionDto> questionDtoList;
}
