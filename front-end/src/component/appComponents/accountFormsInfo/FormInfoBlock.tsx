import React from "react";
import FormInfoRecord from "./FormInfoRecord";
import { v4 as uuid } from 'uuid';
import "../../../style/accauntFormInfo.css"

/**
 * @param state state == TRUE - show available forms, state == FALSE - show owned forms
 * @param formsPassed
 * @param formsOwned
 * @constructor
 */
function FormInfoBlock({state, formsPassed, formsOwned}) {
    // 2021-11-30T14:08:59.124Z
    const formatData = (date: string): string => {
        const substringDate: string = date.substring(11);
        const dateObj = new Date(substringDate);
        const day: number = dateObj.getDay();
        const month: number = dateObj.getMonth();
        return `${day < 10 ? "0" + day : day}-${month < 10 ? "0" + month : month}-${dateObj.getFullYear()}`;
    }

    return (
        <div className="form-info-block">
            <div className="form-info-block-content">
                <div className="form-info-block-header">
                    <h3 id="form-info-block-header-content">
                        {state ? "Passed forms" : "Created forms"}
                    </h3>
                </div>
                <div className="form-info-block-record-list">
                    {
                        state ?
                            formsPassed.map(record =>
                                <FormInfoRecord key={uuid()} state={true} id={record.id}
                                                topicName={record.topicName} answerDate={record.answerDate}
                                                expireDate={null} score={record.formScore}/>)
                            :
                            formsOwned.map(record =>
                                <FormInfoRecord key={uuid()} state={false} id={record.id}
                                                topicName={record.topicName} answerDate={record.expireDate}
                                                expireDate={null} score={0}/>)
                    }
                </div>
            </div>
        </div>
    )
}

export default FormInfoBlock;