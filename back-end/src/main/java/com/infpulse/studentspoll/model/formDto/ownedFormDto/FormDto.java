package com.infpulse.studentspoll.model.formDto.ownedFormDto;

import com.infpulse.studentspoll.model.formDto.passedForm.QuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormDto {

    @Size(max = 2000)
    @NotBlank
    private String topicName;

    @Positive
    private Integer attempts;

    @Future
    private LocalDateTime expireDateTime;

    @NotEmpty
    List<QuestionDto> questionDtoList;
}
