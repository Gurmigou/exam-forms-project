import React from "react";
import {Card} from "primereact/card";
import QuestionTitle from "./QuestionTitle";
import SingleAnswer from "./questionTypes/SingleAnswer";
import MultiAnswer from "./questionTypes/MultiAnswer";
import OpenAnswer from "./questionTypes/OpenAnswer";

function QuestionBlock({question, index}) {
    return (
        <Card style={{background: `yellow`}}>
            <QuestionTitle title="2 + 2 = ?"/>
            {
                question.possibleAnswersDto.map((answer, index) => {
                    if (question.questionType === "SINGLE")
                        return <SingleAnswer answerTitle={answer.possibleAnswer}/>

                    if (question.questionType === "MULTI")
                        return <MultiAnswer answerTitle={answer.possibleAnswer}/>

                    else
                        return <OpenAnswer answerTitle={answer.possibleAnswer}/>
                })
            }
        </Card>
    )
}

export default QuestionBlock