import React, {useEffect, useState} from "react";
import Layout from "../../../navbarFooter/Layout";
// import {passedOwnedFormsStatisticsList} from "../../../../utils/formAnalytics/formStatisticsByUsers";
import axios from "axios";
import { useParams } from 'react-router-dom';
import {getAuthHeader} from "../../../../utils/security/securityUtils";
import FormStatisticsUserBlock from "./FormStatisticsUserBlock";

function FormStatistics() {
    const [ownedFormsStats, setOwnedFormsStats] = useState([]);
    const { id } = useParams()

    useEffect(() => {
        axios.get(`http://localhost:8080/api/forms/valuation/${id}`, {
            headers: getAuthHeader()
        }).then(response => setOwnedFormsStats(response.data))
    }, []);

    return (
        <Layout>
            <div className="all-outer-container">
                <div className="all-outer-container">
                    <div className="form-info-block">
                        <div className="form-info-block-content">
                            {
                                ownedFormsStats.map(((value: any, index) =>
                                    <FormStatisticsUserBlock
                                        index={index + 1}
                                        userName={value.userName}
                                        surname={value.surname}
                                        answerDate={value.answerDate}
                                        resultScore={value.resultScore}
                                        formMaxResult={value.formMaxResult}
                                    />))
                            }
                        </div>
                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default FormStatistics