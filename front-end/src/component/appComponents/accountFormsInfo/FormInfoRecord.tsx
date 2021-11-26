import React from "react";
import {Link} from "react-router-dom";
import "../../../style/accauntFormInfo.css"

/**
 * @param state state == TRUE - show available forms, state == FALSE - show owned forms
 * @param id
 * @param topicName
 * @param answerDate
 * @param expireDate
 * @param score
 */
function FormInfoRecord({state, id, topicName, answerDate, expireDate, score}) {
    return (
        <div className="form-info-record-block">
            <div className="form-info-record-info">
                <div className="form-info-record-title">
                    <h3 id="user-form-text">
                        {state ?
                            <Link to={`form/${id}/view-result`}>
                                {topicName}
                            </Link>
                            :
                            <Link to={`form/${id}/stats`}>
                                {topicName}
                            </Link>
                        }
                    </h3>
                </div>
                <div className="form-info-record-date">
                    <h3 id="user-form-text">
                        {
                            state ? answerDate : expireDate
                        }
                    </h3>
                </div>
            </div>
            <div className="form-info-record-score" id="score-color-yellow">
                <h3 id="user-form-score">
                    {
                        state ? score : <i className="fas fa-chart-line"/>
                    }
                </h3>
            </div>
        </div>
    )
}

export default FormInfoRecord;