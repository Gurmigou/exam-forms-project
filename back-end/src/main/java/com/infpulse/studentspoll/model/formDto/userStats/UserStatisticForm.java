package com.infpulse.studentspoll.model.formDto.userStats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserStatisticForm {
    private String name;
    private List<UserStatisticBlock> listOfBlocks;
}
