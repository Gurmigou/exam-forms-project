import React, {useState} from "react";
import {Card} from "primereact/card";
import {InputText} from "primereact/inputtext";

function QuestionTitleBuilder() {
    const [questionName, setQuestionName] = useState("");

    return (
        <Card style={{
            boxShadow: `none`,
            width: `50%`
        }}>
            <div style={{
                display: `flex`,
                flexDirection: `column`
            }}>
                <div className="flex justify-content-start" style={{marginBottom: `15px`}}>
                    <p style={{fontSize: `22px`, fontWeight: `bold`}}>Question title</p>
                </div>
                <InputText style={{width: `100%`}} value={questionName} onChange={(e) => setQuestionName(e.target.value)} />
            </div>
        </Card>
    )
}

export default QuestionTitleBuilder