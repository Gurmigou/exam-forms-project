package com.infpulse.studentspoll.model.formDto.ownedFormDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OwnedFormStatistic {
    private String topicName;
    private Integer attemptsAmount;
    private Integer averageScore;
    private Integer minimumScore;
    private Integer maximumScore;
}
