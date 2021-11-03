package com.infpulse.studentspoll.model.formDto;

import com.infpulse.studentspoll.model.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class UserAnswerDto {

    private List<Question> listOfUsersBlocks;
}
