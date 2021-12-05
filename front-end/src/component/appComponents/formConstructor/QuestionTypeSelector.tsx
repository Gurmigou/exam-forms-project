import React, {useState} from "react";
import {Card} from "primereact/card";
import {Dropdown} from "primereact/dropdown";

function QuestionTypeSelector() {
    const cities = [
        { name: 'New York', code: 'NY' },
        { name: 'Rome', code: 'RM' },
        { name: 'London', code: 'LDN' },
        { name: 'Istanbul', code: 'IST' },
        { name: 'Paris', code: 'PRS' }
    ];

    return (
        <Card style={{
            boxShadow: `none`,
            width: `50%`,
            // position: `relative`,
            // bottom: `40px`
        }}>
            <div style={{
                // width: `50%`,
                display: `flex`,
                flexDirection: `column`
            }}>
                <div className="flex justify-content-start" style={{marginBottom: `15px`}}>
                    <p style={{fontSize: `22px`, fontWeight: `bold`}}>Question type</p>
                </div>
                <Dropdown style={{width: `100%`}}
                          value={"Test"} options={cities} onChange={() => {}}
                          optionLabel="name" placeholder="Select question type" />
            </div>
        </Card>
    )
}

export default QuestionTypeSelector