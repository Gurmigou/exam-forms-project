package com.infpulse.studentspoll.model.formDto.formHeaders;

import com.infpulse.studentspoll.model.state.FormState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OwnedFormHeader {

    @Positive
    private Long id;

    @NotBlank
    @Size(max = 2000)
    private String topicName;

    @Future
    private LocalDateTime expireDateTime;

    @NotNull
    private FormState formState;
}
