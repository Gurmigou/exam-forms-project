import React from "react";
import {Card} from "primereact/card";
import {Dropdown} from "primereact/dropdown";
import {setQuestionSelectedType} from "../../../../utils/formConstructor/formConstructorUtils";

function QuestionTypeSelector({questionTypes, questionSelectedType, questionList, setQuestionList, index}) {
    return (
        <Card style={{
            boxShadow: `none`,
            width: `50%`,
        }}>
            <div style={{
                display: `flex`,
                flexDirection: `column`
            }}>
                <div className="flex justify-content-start" style={{marginBottom: `15px`}}>
                    <p style={{fontSize: `22px`, fontWeight: `bold`}}>Question type</p>
                </div>
                <Dropdown style={{width: `100%`}}
                          value={questionSelectedType} options={questionTypes}
                          onChange={(e) =>
                              setQuestionSelectedType(questionList, index, e.value, setQuestionList)}
                          optionLabel="name" placeholder="Select question type" />
            </div>
        </Card>
    )
}

export default QuestionTypeSelector