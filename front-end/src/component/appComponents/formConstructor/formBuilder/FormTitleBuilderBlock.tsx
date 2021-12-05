import React, {useState} from "react";
import {Card} from "primereact/card";
import {InputText} from "primereact/inputtext";

function FormTitleBuilderBlock() {
    const [formTitle, setFormTitle] = useState("");
    const [maxAttempts, setMaxAttempts] = useState("");
    const [expiresInDays, setExpireInDays] = useState("");

    const isValidNumber = (num: string): boolean => {
        if (num === "0")
            return false;
        if (num === "")
            return true;
        for (let i = 0; i < num.length; i++) {
            if (num.charAt(i) < '0' || num.charAt(i) > '9')
                return false;
        }
        return true;
    }

    return (
        <Card style={{
            height: `400px`,
            marginBottom: `50px`,
            background: `#839be0`,
            boxShadow: `2px 5px 10px #D0D0D0`
        }}>
            <Card style={{
                boxShadow: `none`,
                width: `100%`
            }}>
                <div style={{
                    display: `flex`,
                    flexDirection: `column`
                }}>
                    <div className="flex justify-content-start" style={{marginBottom: `15px`}}>
                        <p style={{fontSize: `22px`, fontWeight: `bold`}}>Form title</p>
                    </div>
                    <InputText style={{width: `100%`}} value={formTitle}
                               onChange={(e) => setFormTitle(e.target.value)} />
                </div>
            </Card>
            <div style={{
                display: `flex`,
                flexDirection: `row`,
                justifyContent: `space-between`,

                position: `relative`,
                bottom: `30px`
            }}>
                <Card style={{
                    boxShadow: `none`,
                    width: `50%`,
                    borderBottomRightRadius: 0
                }}>
                    <div style={{
                        display: `flex`,
                        flexDirection: `column`
                    }}>
                        <div className="flex justify-content-start" style={{marginBottom: `15px`}}>
                            <p style={{fontSize: `22px`, fontWeight: `bold`}}>Max attempts</p>
                        </div>
                        <InputText style={{width: `100%`}} value={maxAttempts}
                                   onChange={(e) => {
                                       if (isValidNumber(e.target.value))
                                            setMaxAttempts(e.target.value)
                                   }} />
                    </div>
                </Card>
                <Card style={{
                    boxShadow: `none`,
                    width: `50%`,
                    borderBottomLeftRadius: 0
                }}>
                    <div style={{
                        display: `flex`,
                        flexDirection: `column`
                    }}>
                        <div className="flex justify-content-start" style={{marginBottom: `15px`}}>
                            <p style={{fontSize: `22px`, fontWeight: `bold`}}>Expires in (days)</p>
                        </div>
                        <InputText style={{width: `100%`}} value={expiresInDays}
                                   onChange={(e) => {
                                       if (isValidNumber(e.target.value))
                                            setExpireInDays(e.target.value)
                                   }} />
                    </div>
                </Card>
            </div>
        </Card>
    )
}

export default FormTitleBuilderBlock