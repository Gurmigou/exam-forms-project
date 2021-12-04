import React from "react";
import {Link} from "react-router-dom";
import "../../../style/accauntFormInfo.css"

/**
 * @param state state == TRUE - show available forms, state == FALSE - show owned forms
 * @param id skipped
 * @param topicName skipped
 * @param answerDate skipped
 * @param expireDate skipped
 */
function FormInfoRecord({state, id, topicName, localDateTime, formattedAnswerDate, expireDate, gotScore, maxScore}) {

    const less = (n1: number, n2: number): boolean => {
        return n1 < n2 || Math.abs(n1 - n2) < 0.0001;
    }

    return (
        <div className="form-info-record-block">
            <div className="form-info-record-info">
                <div className="form-info-record-title">
                    <h3 id="user-form-text">
                        {state ?
                            <Link id="user-form-text-link" to={`/form/${id}/${localDateTime}/view-result`}>
                                {topicName}
                            </Link>
                            :
                            <Link id="user-form-text-link" to={`/form/${id}/view-stats`}>
                                {topicName}
                            </Link>
                        }
                    </h3>
                </div>
                <div className="form-info-record-date">
                    <h3 id="user-form-text">
                        {
                            state ? formattedAnswerDate : expireDate
                        }
                    </h3>
                </div>
            </div>
            <div className="form-info-record-score"
                 id={!state ? "score-color-blue" : less(gotScore, 0.6) ?
                     "score-color-red" : less(gotScore / maxScore, 0.75) ? "score-color-yellow" : "score-color-green"}>
                <h3 id="user-form-score">
                    {
                        state ? gotScore : <i className="fas fa-chart-line"/>
                    }
                </h3>
            </div>
        </div>
    )
}

export default FormInfoRecord;