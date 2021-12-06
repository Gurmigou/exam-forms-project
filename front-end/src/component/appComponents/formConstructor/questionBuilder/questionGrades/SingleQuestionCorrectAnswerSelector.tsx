import React from "react";
import {Dropdown} from "primereact/dropdown";
import {
    getCorrectAnswerSingleQuestion,
    setCorrectAnswerSingleQuestion
} from "../../../../../utils/formConstructor/formConstructorUtils";

function SingleQuestionCorrectAnswerSelector({numOfAnswers, question, setQuestionList}) {
    const createDropdown = () => {
        const arr: {name: string}[] = [];
        for (let i = 0; i < numOfAnswers; i++) {
            arr.push({name: (i + 1).toString()})
        }
        return arr;
    }

    return (
        <div style={{marginBottom: `25px`, width: `95%`, marginLeft: `20px`}}>
            <div style={{display: `flex`, flexDirection: `column`}}>
                <div className="flex justify-content-start" style={{marginBottom: `15px`}}>
                    <p style={{fontSize: `22px`, fontWeight: `bold`}}>Correct answer</p>
                </div>
                <Dropdown style={{width: `20%`}}
                          value={{name : getCorrectAnswerSingleQuestion(question)}} options={createDropdown()}
                          onChange={(e) => setCorrectAnswerSingleQuestion(
                              question, parseInt(e.value.name) - 1, setQuestionList)}
                          optionLabel="name" placeholder="Answer" />
            </div>
        </div>
    )
}

export default SingleQuestionCorrectAnswerSelector