package com.infpulse.studentspoll.model.formDto.ownedFormDto;

import com.infpulse.studentspoll.model.formDto.passedForm.QuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnedFormDto {

    @Size(max = 2000)
    @NotBlank
    private String topicName;

    @Positive
    private Integer maxAttempts;

    @Future
    private Timestamp expireDateTime;

    @NotEmpty
    List<QuestionDto> questionDtoList;
}
