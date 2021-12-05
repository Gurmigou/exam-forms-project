import React, {useState} from "react";
import {Card} from "primereact/card";
import {Dropdown} from "primereact/dropdown";

function QuestionTypeSelector() {
    const questionTypes = [
        { name: 'Single answer question', type: 'SAQ' },
        { name: 'Multiple answer question', type: 'MAQ' },
        { name: 'Open-ended question', type: 'OEQ' }
    ];

    const [questionSelectedType, setQuestionSelectedType] = useState({name: "", type: ""});

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
                          onChange={(e) => setQuestionSelectedType(e.value)}
                          optionLabel="name" placeholder="Select question type" />
            </div>
        </Card>
    )
}

export default QuestionTypeSelector