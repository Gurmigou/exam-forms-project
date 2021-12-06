import React from "react";
import {InputText} from "primereact/inputtext";
import {Checkbox} from "primereact/checkbox";
import {addAnswer, deleteAnswer, isCorrectMultipleAnswerQuestion, isNonNegativeNumber,
        setAnswerGrade, setAnswerValue, setCorrectMultipleAnswerQuestion}
    from "../../../../../utils/formConstructor/formConstructorUtils";

function MultipleAnswerBuilder({question, setQuestionList, answer, answerIndex}) {
    return (
        <div>
            <div style={{marginBottom: `10px`, width: `95%`, marginLeft: `20px`}}>
                <div style={{display: `flex`, flexDirection: `row`, justifyContent: `space-between`, alignItems: `center`}}>
                    <div style={{width: `60%`, display: `flex`, flexDirection: `row`, alignItems: `center`}}>
                        <div style={{marginRight: `10px`, fontWeight: `bold`}}>
                            <p>{`${answerIndex + 1}.`}</p>
                        </div>
                        <div style={{width: `100%`}}>
                            <InputText style={{width: `100%`}} placeholder="Possible answer"
                                       value={answer.possibleAnswer}
                                       onChange={(e) =>
                                           setAnswerValue(answer, e.target.value, setQuestionList)} />
                        </div>
                    </div>
                    <div style={{
                        width: `40%`, display: `flex`, flexDirection: `row`, justifyContent: `space-around`}}>
                        <div style={{width: `70%`, display: `flex`, flexDirection: `row`,
                            justifyContent: `space-around`, alignItems: `center`}}>
                            <div style={{width: ``}}>
                                <InputText style={{width: `55%`}} placeholder="Grade" value={answer.answerValue}
                                           onChange={(e) => {
                                               if (isNonNegativeNumber(e.target.value))
                                                   setAnswerGrade(answer, e.target.value, setQuestionList)
                                           }} />
                            </div>
                            <div>
                                <p style={{fontWeight: `bold`, fontSize: `18px`, marginRight: `10px`}}>correct: </p>
                            </div>
                            <div>
                                <Checkbox inputId="binary" checked={isCorrectMultipleAnswerQuestion(question, answerIndex)}
                                          onChange={e => setCorrectMultipleAnswerQuestion(
                                              question, answerIndex, e.checked, setQuestionList)}/>
                            </div>
                        </div>
                        <div style={{width: `30%`, display: `flex`, flexDirection: `row`,
                            justifyContent: `space-evenly`, alignItems: `center`}}>
                            <i onClick={() => addAnswer(question, setQuestionList)}
                               className="fas fa-plus-circle fa-2x"/>
                            <i onClick={() => deleteAnswer(question, answerIndex, setQuestionList)}
                               className="fas fa-trash-alt fa-2x"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default MultipleAnswerBuilder