package com.infpulse.studentspoll.model.formDto.submitForm;

import com.infpulse.studentspoll.model.formDto.passedForm.QuestionDto;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitFormDto {
    @Size(max = 2000)
    @NotBlank
    private String topicName;

    @Positive
    private Integer attempts;

    @Positive
    private Integer plusDays;

    @NotEmpty
    List<QuestionDto> questionDtoList;
}
