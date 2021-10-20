package com.infpulse.studentspoll.model.formDto.userStats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserStatisticField {
    private String variant;
    private Integer value;
    private FieldStatus status;
}
