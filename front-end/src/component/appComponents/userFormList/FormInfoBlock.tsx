import React from "react";
import FormInfoRecord from "./FormInfoRecord";
import { v4 as uuid } from 'uuid';
import "../../../style/accauntFormInfo.css"
import {formatDate} from "../../../utils/formList/userFormList";

/**
 * @param state state == TRUE - show available forms, state == FALSE - show owned forms
 * @param formsPassed
 * @param formsOwned
 * @constructor
 */
function FormInfoBlock({state, formsPassed, formsOwned}) {
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
                                <FormInfoRecord
                                    key={uuid()} state={true} id={record.id}
                                    topicName={record.topicName}
                                    localDateTime={record.answerDate}
                                    formattedAnswerDate={formatDate(record.answerDate)}
                                    expireDate={null} gotScore={record.formScore}
                                    maxScore={record.formMaxResult}/>)
                            :
                            formsOwned.map(record =>
                                <FormInfoRecord
                                    key={uuid()} state={false} id={record.id}
                                    topicName={record.topicName}
                                    localDateTime={null}
                                    formattedAnswerDate={null}
                                    expireDate={formatDate(record.expireDateTime)} gotScore={0}
                                    maxScore={0}/>)
                    }
                </div>
            </div>
        </div>
    )
}

export default FormInfoBlock;