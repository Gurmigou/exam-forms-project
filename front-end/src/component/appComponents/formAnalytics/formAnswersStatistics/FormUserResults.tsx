import React, {useEffect, useState} from "react";
import StatisticsBlock from "./StatisticsBlock";
import Layout from "../../../navbarFooter/Layout";
import { useParams } from 'react-router-dom'
import axios from "axios";
import {getAuthHeader} from "../../../../utils/security/securityUtils";
import {defaultPassedFormStats} from "../../../../utils/formAnswerStatistics/formAnswerStatistics";

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
    * */

    return (
        <Layout>
            <div className="all-outer-container">
                {
                    formStats.formQuestions.map((value, index) =>
                        <StatisticsBlock id={index + 1} questionName={value.questionName}
                                         possibleAnswers={value.possibleAnswersDto}/>)
                }

                {/*<StatisticsBlock/>*/}
                {/*<StatisticsBlock/>*/}
                {/*<StatisticsBlock/>*/}
                {/*<StatisticsBlock/>*/}
                {/*<StatisticsBlock/>*/}
                {/*<StatisticsBlock/>*/}

            </div>
        </Layout>
    )
}

export default FormUserResults