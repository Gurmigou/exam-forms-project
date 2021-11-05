package com.infpulse.studentspoll.model.formDto.newForm;

import com.infpulse.studentspoll.model.formDto.passedForm.QuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NewFormDto {
    @NotBlank
    @Size(max = 2000)
    private String topicName;

    @Future
    private Timestamp expireDate;
    @Positive
    private Integer maxAttempts;

    @NotEmpty
    private List<QuestionDto> questionDtoList;
}
