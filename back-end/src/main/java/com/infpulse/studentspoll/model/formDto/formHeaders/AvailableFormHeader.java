package com.infpulse.studentspoll.model.formDto.formHeaders;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AvailableFormHeader {

    @Positive
    private Long id;

    @NotBlank
    @Size(max = 2000)
    private String topicName;

    @PositiveOrZero
    private Integer formScore;

    @Past
    private LocalDateTime answerDate;

    @Positive
    private Integer formMaxResult;

}
