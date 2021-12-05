import React, {useState} from "react";
import {InputText} from "primereact/inputtext";

function NonTestAnswerBuilder({index}) {
    const [value, setValue] = useState("");

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
                    <div style={{marginRight: `10px`, fontWeight: `bold`}}>
                        <p>{`${index}.`}</p>
                    </div>
                    <div style={{width: `90%`}}>
                        <InputText style={{width: `100%`}} placeholder="Question" value={value} onChange={(e) => setValue(e.target.value)} />
                    </div>
                </div>
                <div style={{
                    width: `15%`,
                    display: `flex`,
                    flexDirection: `row`,
                    justifyContent: `space-around`
                }}>
                    <i className="fas fa-plus-circle fa-2x"/>
                    <i className="fas fa-trash-alt fa-2x"/>
                </div>
            </div>
            <div style={{
                display: `flex`,
                flexDirection: `row`,
                justifyContent: `space-between`,
                alignItems: `center`,
                marginTop: `15px`
            }}>
                <div style={{
                    width: `85%`,
                    display: `flex`,
                    flexDirection: `row`,
                    alignItems: `center`,
                    marginLeft: `1.5rem`
                }}>
                    <div style={{width: `90%`}}>
                        <InputText style={{width: `100%`}} placeholder="Possible answer" value={value} onChange={(e) => setValue(e.target.value)} />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default NonTestAnswerBuilder