import React from "react";
import QuestionTitleBuilder from "./QuestionTitleBuilder";
import {Card} from "primereact/card";
import QuestionTypeSelector from "./QuestionTypeSelector";
import SingleQuestionAnswersBuilder from "./questionTypes/SingleQuestionAnswersBuilder";
import MultipleQuestionAnswerBuilder from "./questionTypes/MultipleQuestionAnswerBuilder";
import NonTestAnswerBuilder from "./questionTypes/NonTestAnswerBuilder";

function FormBuilderBlock() {
    return (
        <Card>
            <div style={{
                display: `flex`,
                flexDirection: `row`,
                justifyContent: `space-between`
            }}>
                <QuestionTitleBuilder/>
                <QuestionTypeSelector/>
            </div>
            <div>
                <NonTestAnswerBuilder index={1}/>
                <NonTestAnswerBuilder index={2}/>
                <SingleQuestionAnswersBuilder index={3}/>
                <MultipleQuestionAnswerBuilder index={4}/>
                <NonTestAnswerBuilder index={1}/>
            </div>
        </Card>
    )
}

export default FormBuilderBlock;