import React from "react";
import {InputText} from "primereact/inputtext";
import {setOpenAnswer} from "../../../../utils/form/formUtils";

function OpenAnswer({answerList, answerIndex, setRender}) {
    return (
        <div className="flex justify-content-start" style={{width: `100%`, margin: `30px`}}>
            <InputText style={{width: `90%`, fontSize: `20px`}}
                       placeholder={"My answer"}
                       value={answerList[answerIndex].possibleAnswer}
                       onChange={(e) => setOpenAnswer(
                           answerList[answerIndex], e.target.value, setRender)} />
        </div>
    )
}

export default OpenAnswer