import React from "react";
import {Checkbox} from "primereact/checkbox";
import {InputText} from "primereact/inputtext";

function OpenAnswer({answerTitle}) {
    return (
        <div className="flex justify-content-center" style={{width: `100%`, margin: `30px`}}>
            <div style={{width: `90%`, display: `flex`, flexDirection: `row`}}>
                <InputText value={"value"} /*onChange={(e) => setValue(e.target.value)}*/ />
            </div>
        </div>
    )
}

export default OpenAnswer