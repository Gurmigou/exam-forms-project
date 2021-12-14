import React, {useEffect, useState} from "react";
import "../../../../style/accauntFormInfo.css";
import StatisticsRecord from "./StatisticsRecord";
import {Card} from "primereact/card";

function StatisticsBlock({id, questionName, possibleAnswers}) {
    const green: string = "#469346";
    const lightGreen: string = "#70ce6e";
    const red: string = "#b93c3c";
    const defaultColor: string = "rgba(104, 165, 248, 0.68)";

    const getAnswerColor = (status: string): string => {
        if (status === "USER_CORRECT")
            return green;
        if (status === "WRONG")
            return red;
        if (status === "CORRECT")
            return lightGreen;
        return defaultColor;
    }

    const calculateQuestionScore = (possibleAnswers): number => {
        let gotScore: number = 0;
        for (let i = 0; i < possibleAnswers.length; i++) {
            const answer = possibleAnswers[i];
            if (answer.answerStatus === "USER_CORRECT")
                gotScore += answer.answerValue;
        }
        return gotScore;
    }

    const calculateMaxScore = (possibleAnswers) => {
        let max: number = 0;
        for (let i = 0; i < possibleAnswers.length; i++)
            max += possibleAnswers[i].answerValue;
        return max;
    }

    return (
        <div>
            <Card style={{background: `#8299e0`, marginBottom: `20px`, fontWeight: `bold`,
                color: `black`, fontSize: `22px`}}>
                <p style={{textAlign: "left"}}>
                    {`${id}) ${questionName}`}
                </p>
            </Card>
            <Card style={{background: `rgba(184, 213, 252, 0.68)`, marginBottom: `45px`}}>
                {
                    possibleAnswers.map((record, index) => <StatisticsRecord
                            color={getAnswerColor(record.answerStatus)}
                            index={index + 1}
                            text={record.possibleAnswer}/>)
                }
                <div className="flex justify-content-end">
                    <div style={{marginRight: `10px`, marginTop: `10px`}}>
                        <h3>
                            {`${calculateQuestionScore(possibleAnswers)}/${calculateMaxScore(possibleAnswers)}`}
                        </h3>
                    </div>
                </div>
            </Card>
        </div>
    )
}

export default StatisticsBlock