import React from "react";
import {Checkbox} from "primereact/checkbox";
import {setMultiAnswer} from "../../../../utils/form/formUtils";

function MultiAnswer({answerTitle, answerList, answerIndex, setRender}) {
    return (
        <div className="flex justify-content-center" style={{width: `100%`, margin: `30px`}}>
            <div style={{width: `90%`, display: `flex`, flexDirection: `row`, alignItems: `center`}}>
                <Checkbox inputId="binary"
                          value={answerList[answerIndex].answerStatus}
                          checked={answerList[answerIndex].answerStatus === "CORRECT"}
                          onChange={e => setMultiAnswer(answerList, answerIndex, !e.value, setRender)}/>
                <p style={{marginLeft: `20px`, fontSize: `18px`}}>
                    {answerTitle}
                </p>
            </div>
        </div>
    )
}

export default MultiAnswer