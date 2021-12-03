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
function FormInfoRecord({state, id, topicName, answerDate, expireDate, gotScore, maxScore}) {
    const less = (n1: number, n2: number): boolean => {
        console.log("n1: " + n1);
        console.log("n2: " + n2);
        console.log(n1 < n2 || Math.abs(n1 - n2) < 0.0001);

        return n1 < n2 || Math.abs(n1 - n2) < 0.0001;
    }

    return (
        <div className="form-info-record-block">
            <div className="form-info-record-info">
                <div className="form-info-record-title">
                    <h3 id="user-form-text">
                        {state ?
                            <Link id="user-form-text-link" to={`/form/${id}/view-result`}>
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
                            state ? answerDate : expireDate
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