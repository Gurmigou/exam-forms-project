import React from "react";
import {Card} from "primereact/card";
import QuestionTitle from "./QuestionTitle";
import SingleAnswer from "./questionTypes/SingleAnswer";
import MultiAnswer from "./questionTypes/MultiAnswer";
import OpenAnswer from "./questionTypes/OpenAnswer";

function QuestionBlock({question, index, setRender}) {
    return (
        <Card style={{boxShadow: `3px 3px 12px 2px #bebebe`, marginTop: `25px`}}>
            <QuestionTitle index={index + 1} title={question.questionName}/>
            {
                question.possibleAnswersDto.map((answer, index) => {
                    if (question.questionType === "SINGLE")
                        return <SingleAnswer answerTitle={answer.possibleAnswer}
                                             answerList={question.possibleAnswersDto}
                                             answerIndex={index}
                                             setRender={setRender}/>

                    if (question.questionType === "MULTI")
                        return <MultiAnswer answerTitle={answer.possibleAnswer}
                                            answerList={question.possibleAnswersDto}
                                            answerIndex={index}
                                            setRender={setRender}/>

                    else
                        return <OpenAnswer answerList={question.possibleAnswersDto}
                                           answerIndex={index}
                                           setRender={setRender}/>
                })
            }
        </Card>
    )
}

export default QuestionBlock