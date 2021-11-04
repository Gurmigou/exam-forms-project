package com.infpulse.studentspoll.model.formDto.statistics;

import com.infpulse.studentspoll.model.entity.PossibleAnswer;

import java.util.List;

public class QuestionDto {
    private String questionName;
    private boolean isComputed;
    private List<PossibleAnswerDto> fieldDtoList;
}
