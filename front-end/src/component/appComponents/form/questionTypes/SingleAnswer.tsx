import React from "react";
import {RadioButton} from "primereact/radiobutton";
import {Card} from "primereact/card";

function SingleAnswer({answerTitle}) {
    return (
        <div className="flex justify-content-center" style={{width: `100%`, margin: `30px`}}>
            <div style={{
                width: `90%`,
                display: `flex`,
                flexDirection: `row`
            }}>
                <RadioButton inputId="city1" name="city"
                             value="Chicago" />
                <p>
                    {answerTitle}
                </p>
            </div>
        </div>
    )
}

export default SingleAnswer