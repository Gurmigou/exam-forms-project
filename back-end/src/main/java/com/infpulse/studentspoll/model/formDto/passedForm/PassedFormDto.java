package com.infpulse.studentspoll.model.formDto.passedForm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.security.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PassedFormDto {
    @NotBlank
    private String topicName;
    @Positive
    private Integer formScore;

    private Timestamp answerDate;
    @NotEmpty
    private List<QuestionDto> formQuestions;
}
