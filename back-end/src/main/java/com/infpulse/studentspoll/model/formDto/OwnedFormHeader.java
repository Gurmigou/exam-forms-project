package com.infpulse.studentspoll.model.formDto;

import com.infpulse.studentspoll.model.state.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OwnedFormHeader {

    @Positive
    private Long id;

    @NotBlank
    private String topicName;

    @Future
    private LocalDateTime expireDate;

    @NotNull
    private State state;

}
