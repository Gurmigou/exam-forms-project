import React from "react";
import {Checkbox} from "primereact/checkbox";

function MultiAnswer({answerTitle}) {
    return (
        <div className="flex justify-content-center" style={{width: `100%`, margin: `30px`}}>
            <div style={{
                width: `90%`,
                display: `flex`,
                flexDirection: `row`
            }}>
                <Checkbox /*onChange={e => setChecked(e.checked)}*/ checked={true}/>
                <p>
                    {answerTitle}
                </p>
            </div>
        </div>
    )
}

export default MultiAnswer