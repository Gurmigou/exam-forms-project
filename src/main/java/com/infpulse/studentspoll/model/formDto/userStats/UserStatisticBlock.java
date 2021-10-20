package com.infpulse.studentspoll.model.formDto.userStats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserStatisticBlock {
    private String question;
    private List<UserStatisticField> lisfOfVariants;
}
