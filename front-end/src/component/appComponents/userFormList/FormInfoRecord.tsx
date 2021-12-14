import React, {useRef} from "react";
import {Link} from "react-router-dom";
import "../../../style/accauntFormInfo.css"
import {Toast} from "primereact/toast";

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

    const toast = useRef(null);

    const showBottomRight = () => {
        // @ts-ignore
        toast.current.show({
            severity:'success',
            summary: 'URL of the form was copied',
            life: 3000
        });
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
            {
                state ?
                    <div className="form-info-record-score" id={less(gotScore / maxScore, 0.6) ? "score-color-red" :
                        less(gotScore / maxScore, 0.75) ? "score-color-yellow" : "score-color-green"}>
                        <h3 id="user-form-score">
                            {gotScore}
                        </h3>
                    </div>
                :
                    <div className="form-info-record-score" id="score-color-blue" onClick={() => {
                        navigator.clipboard.writeText(`http://localhost:3000/form/${id}`);
                        showBottomRight();
                    }}>
                        <h3 id="user-form-score">
                            <i className="fas fa-copy"/>
                        </h3>

                    </div>
            }
            <Toast ref={toast} position="bottom-right"/>
        </div>
    )
}

export default FormInfoRecord;