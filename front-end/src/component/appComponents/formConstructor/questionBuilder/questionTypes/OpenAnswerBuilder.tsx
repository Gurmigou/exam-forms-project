import React from "react";
import {InputText} from "primereact/inputtext";
import {isNonNegativeNumber, setAnswerGrade, setAnswerValue, setCorrectOpenAnswerQuestion}
    from "../../../../../utils/formConstructor/formConstructorUtils";

function OpenAnswerBuilder({setQuestionList, answer}) {
    return (
        <div style={{marginBottom: `35px`, width: `95%`, marginLeft: `20px`, display: `flex`, flexDirection: `column`}}>
            <div style={{display: `flex`, flexDirection: `row`, justifyContent: `space-between`, alignItems: `center`}}>
                <div style={{width: `85%`, display: `flex`, flexDirection: `row`, alignItems: `center`}}>
                    <div style={{width: `100%`}}>
                        <InputText style={{width: `100%`}} placeholder="Possible answer"
                                   value={answer.possibleAnswer}
                                   onChange={(e) => {
                                       setAnswerValue(answer, e.target.value, setQuestionList);
                                       setCorrectOpenAnswerQuestion(answer, setQuestionList);
                                   }}
                        />
                    </div>
                </div>
                <div style={{width: `25%`, display: `flex`, flexDirection: `row`,
                    justifyContent: `end`, alignItems: `center`}}>
                    <InputText style={{width: `50%`}} placeholder="Grade" value={answer.answerValue}
                               onChange={(e) => {
                                   if (isNonNegativeNumber(e.target.value))
                                       setAnswerGrade(answer, e.target.value, setQuestionList)
                               }} />
                </div>
            </div>
        </div>
    )
}

export default OpenAnswerBuilder