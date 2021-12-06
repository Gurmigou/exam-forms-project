import React, {useState} from "react";
import {InputText} from "primereact/inputtext";
import {isNonNegativeNumber} from "../../../../../utils/formConstructor/formConstructorUtils";

function NonTestAnswerBuilder() {
    const [value, setValue] = useState("");
    const [grade, setGrade] = useState("");

    return (
        <div style={{
            marginBottom: `35px`,
            width: `95%`,
            marginLeft: `20px`,
            display: `flex`,
            flexDirection: `column`
        }}>
            <div style={{
                display: `flex`,
                flexDirection: `row`,
                justifyContent: `space-between`,
                alignItems: `center`
            }}>
                <div style={{
                    width: `85%`,
                    display: `flex`,
                    flexDirection: `row`,
                    alignItems: `center`
                }}>
                    <div style={{width: `100%`}}>
                        <InputText style={{width: `100%`}} placeholder="Possible answer" value={value}
                                   onChange={(e) => setValue(e.target.value)} />
                    </div>
                </div>
                <div style={{
                    width: `25%`,
                    display: `flex`,
                    flexDirection: `row`,
                    justifyContent: `end`,
                    alignItems: `center`
                }}>
                    <InputText style={{width: `50%`}} placeholder="Grade" value={grade}
                               onChange={(e) => {
                                   if (isNonNegativeNumber(e.target.value))
                                       setGrade(e.target.value)
                               }} />
                </div>
            </div>
        </div>
    )
}

export default NonTestAnswerBuilder