package com.infpulse.studentspoll.model.formDto;

import com.infpulse.studentspoll.model.entity.Answer;
import com.infpulse.studentspoll.model.entity.Block;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class FormDto {
    @NotBlank
    @Size(min = 3, max = 255)

    private String topicName;

    @Future
    private LocalDateTime expireDate;

    @NotEmpty
    private List<Block> listOfBlocks;

    private Answer answer;
}
