import React, {useEffect, useState} from "react";
import StatisticsBlock from "./StatisticsBlock";
import Layout from "../../../navbarFooter/Layout";
import { useParams } from 'react-router-dom'
import axios from "axios";
import {getAuthHeader} from "../../../../utils/security/securityUtils";
import {defaultPassedFormStats} from "../../../../utils/formAnalytics/formAnswerStatistics";
import {Card} from "primereact/card";

function FormUserResults() {
    const [formStats, setFormStats] = useState(defaultPassedFormStats);
    const { id, date } = useParams()

    useEffect(() => {
        axios.get(`http://localhost:8080/api/answers/${id}/${date}`, {
            headers: getAuthHeader()
        }).then(response => setFormStats(response.data))
    }, [id, date, formStats.formScore]);

/*
    export const defaultPassedFormStats = {
    topicName: "Topic name",
    formScore: 0,
    answerDate: "2021-11-26T14:08:59.124Z",
    formQuestions: [
        {
        questionName: "Question 1",
        questionType: "TYPE",
        possibleAnswersDto: [
            {
                possibleAnswer: "Answer 1",
                answerStatus: "CORRECT",
                answerValue: 0
            }
        ]
    }
    ]
}
*/

    return (
        <Layout>
            <div className="all-outer-container">
                <div className="form-info-block">
                    <div className="form-info-block-content">
                        <Card style={{background: `#8299e0`, marginBottom: `35px`}}>
                            <h3 style={{fontSize: `26px`, fontWeight: `bold`, color: `black`}}>
                                {formStats.topicName}
                            </h3>
                        </Card>
                        {
                            formStats.formQuestions.map((value, index) =>
                                <StatisticsBlock id={index + 1} questionName={value.questionName}
                                                 possibleAnswers={value.possibleAnswersDto}/>)
                        }
                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default FormUserResults