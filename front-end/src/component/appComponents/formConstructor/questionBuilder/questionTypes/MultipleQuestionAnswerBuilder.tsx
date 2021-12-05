import React, {useState} from "react";
import {InputText} from "primereact/inputtext";
import {Checkbox} from "primereact/checkbox";

function MultipleQuestionAnswerBuilder({index}) {
    const [value, setValue] = useState("");
    const [checked, setChecked] = useState(false);

    return (
        <div>
            <div style={{marginBottom: `10px`, width: `95%`, marginLeft: `20px`}}>
                <div style={{
                    display: `flex`,
                    flexDirection: `row`,
                    justifyContent: `space-between`,
                    alignItems: `center`
                }}>
                    <div style={{
                        width: `70%`,
                        display: `flex`,
                        flexDirection: `row`,
                        alignItems: `center`
                    }}>
                        <div style={{marginRight: `10px`, fontWeight: `bold`}}>
                            <p>{`${index}.`}</p>
                        </div>
                        <div style={{width: `100%`}}>
                            <InputText style={{width: `100%`}} placeholder="Question" value={value} onChange={(e) => setValue(e.target.value)} />
                        </div>
                    </div>
                    <div style={{
                        width: `30%`,
                        display: `flex`,
                        flexDirection: `row`,
                        justifyContent: `space-around`
                    }}>
                        <div style={{
                            width: `50%`,
                            display: `flex`,
                            flexDirection: `row`,
                            justifyContent: `space-around`,
                            alignItems: `center`
                        }}>
                            <div>
                                <p style={{fontWeight: `bold`}}>correct: </p>
                            </div>
                            <div>
                                <Checkbox inputId="binary" checked={checked} onChange={e => setChecked(e.checked)} />
                            </div>
                        </div>
                        <div style={{
                            width: `50%`,
                            display: `flex`,
                            flexDirection: `row`,
                            justifyContent: `space-around`
                        }}>
                            <i className="fas fa-plus-circle fa-2x"/>
                            <i className="fas fa-trash-alt fa-2x"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default MultipleQuestionAnswerBuilder