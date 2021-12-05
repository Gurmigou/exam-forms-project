import React from "react";
import QuestionTitleBuilder from "./QuestionTitleBuilder";
import {Card} from "primereact/card";
import QuestionTypeSelector from "./QuestionTypeSelector";
import SingleQuestionAnswersBuilder from "./questionTypes/SingleQuestionAnswersBuilder";
import MultipleQuestionAnswerBuilder from "./questionTypes/MultipleQuestionAnswerBuilder";
import NonTestAnswerBuilder from "./questionTypes/NonTestAnswerBuilder";

function QuestionBuilderBlock() {
    return (
        <Card style={{
            boxShadow: `2px 5px 10px #D0D0D0`
        }}>
            <div style={{
                display: `flex`,
                flexDirection: `row`,
                justifyContent: `space-between`
            }}>
                <QuestionTitleBuilder/>
                <QuestionTypeSelector/>
            </div>
            <div>
                {/*<NonTestAnswerBuilder index={1}/>*/}
                {/*<NonTestAnswerBuilder index={2}/>*/}
                {/*<SingleQuestionAnswersBuilder index={3}/>*/}
                {/*<MultipleQuestionAnswerBuilder index={4}/>*/}
                {/*<NonTestAnswerBuilder index={1}/>*/}
            </div>
            <div style={{
                display: `flex`,
                justifyContent: `end`
            }}>
                <div style={{
                    width: `30%`,
                    display: `flex`,
                    justifyContent: `end`,
                    alignItems: `center`,
                    marginRight: `20px`
                }}>
                    <div style={{
                        width: `40%`,
                        display: `flex`,
                        justifyContent: `space-around`
                    }}>
                        <i className="fas fa-plus-circle fa-2x"/>
                        <i className="fas fa-trash-alt fa-2x"/>
                    </div>
                </div>
            </div>
        </Card>
    )
}

export default QuestionBuilderBlock;