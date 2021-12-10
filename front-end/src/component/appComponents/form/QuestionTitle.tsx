import React from "react";
import {Card} from "primereact/card";

function QuestionTitle({index, title}) {
    return (
        <Card style={{height: `80px`, boxShadow: `none`}}>
            <div style={{display: `flex`, flexDirection: `row`, fontSize: `22px`}}>
                <p style={{marginRight: `20px`, fontWeight: `bold`}}>
                    {`${index}.`}
                </p>
                <p style={{fontWeight: 500}}>
                    {title}
                </p>
            </div>
        </Card>
    )
}

export default QuestionTitle