import React from "react";
import {RadioButton} from "primereact/radiobutton";
import {setSingleAnswer} from "../../../../utils/form/formUtils";

function SingleAnswer({answerTitle, answerList, answerIndex, setRender}) {
    return (
        <div className="flex justify-content-center" style={{width: `100%`, margin: `30px`}}>
            <div style={{width: `90%`, display: `flex`, flexDirection: `row`, alignItems: `center`}}>
                <RadioButton value={answerList[answerIndex].possibleAnswer}
                             checked={answerList[answerIndex].answerStatus === "CORRECT"}
                             onChange={e => setSingleAnswer(answerList, answerIndex, setRender)}/>
                <p style={{marginLeft: `20px`, fontSize: `20px`}}>
                    {answerTitle}
                </p>
            </div>
        </div>
    )
}

export default SingleAnswer