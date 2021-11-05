package com.infpulse.studentspoll.model.entity;

import com.infpulse.studentspoll.model.state.QuestionType;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class UserAnswerObject implements Serializable {

    private QuestionType status;
    private Long singleAnswer;
    private List<Long> manyAnswers;
    private String freeAnswer;

    /*Using Object is dangerous try to implement it or give me an idea how to make it type-safe*/
    public Object getAnswer() {
        if (status == QuestionType.FREE) {
            return freeAnswer;
        } else if (status == QuestionType.MULTI) {
            return manyAnswers;
        }
        return singleAnswer;
    }
}
