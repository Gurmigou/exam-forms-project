package com.infpulse.studentspoll.model.formDto.formHeaders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class UserFormValuation {
    private String userName;
    private String surname;
    private Timestamp answerDate;
    private Integer resultScore;
}
