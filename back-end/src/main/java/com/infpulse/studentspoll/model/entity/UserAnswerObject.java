package com.infpulse.studentspoll.model.entity;

import com.infpulse.studentspoll.model.state.QuestionType;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class UserAnswerObject<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 2347263904L;
    private QuestionType type;
    //    Add exception for jackson
    private T answer;
}
