import React from "react";
import {InputText} from "primereact/inputtext";
import {addAnswer, deleteAnswer, deleteQuestion, isNonNegativeNumber, setAnswerGrade, setAnswerValue}
    from "../../../../../utils/formConstructor/formConstructorUtils";

function SingleQuestionAnswersBuilder({question, setQuestionList, answer, answerIndex}) {
    return (
        <div style={{marginBottom: `25px`, width: `95%`, marginLeft: `20px`}}>
            <div style={{display: `flex`, flexDirection: `row`, justifyContent: `space-between`, alignItems: `center`}}>
                <div style={{width: `70%`, display: `flex`, flexDirection: `row`, alignItems: `center`}}>
                    <div style={{marginRight: `10px`, fontWeight: `bold`}}>
                        <p>{`${answerIndex + 1}.`}</p>
                    </div>
                    <div style={{width: `90%`}}>
                        <InputText style={{width: `100%`}} placeholder="Possible answer"
                                   value={answer.possibleAnswer}
                                   onChange={(e) =>
                                       setAnswerValue(answer, e.target.value, setQuestionList)} />
                    </div>
                </div>
                <div style={{width: `25%`, display: `flex`, flexDirection: `row`,
                            justifyContent: `end`, alignItems: `center`}}>
                    <div>
                        <InputText style={{width: `50%`}} placeholder="Grade" value={answer.answerValue}
                                   onChange={(e) => {
                                       if (isNonNegativeNumber(e.target.value))
                                            setAnswerGrade(answer, e.target.value, setQuestionList)
                                   }} />
                    </div>
                </div>
                <div style={{width: `15%`, display: `flex`, flexDirection: `row`, justifyContent: `space-around`}}>
                    <i onClick={() => addAnswer(question, setQuestionList)} className="fas fa-plus-circle fa-2x"/>
                    <i onClick={() => deleteAnswer(question, answerIndex, setQuestionList)} className="fas fa-trash-alt fa-2x"/>
                </div>
            </div>
        </div>
    )
}

export default SingleQuestionAnswersBuilder