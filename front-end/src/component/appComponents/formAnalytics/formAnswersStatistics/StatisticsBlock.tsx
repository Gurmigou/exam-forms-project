import React from "react";
import "../../../../style/accauntFormInfo.css";
import StatisticsRecord from "./StatisticsRecord";
import {Card} from "primereact/card";

function StatisticsBlock({id, questionName, possibleAnswers}) {
    const green: string = "#4bce49";
    const red: string = "#d74646";
    const defaultColor: string = "rgba(104, 165, 248, 0.68)";

    const getAnswerColor = (status: string): string => {
        // TODO
        // ...
        return defaultColor;
    }

    return (
        <div className="form-info-block">
            <div className="form-info-block-content">
                <Card style={{
                    background: `#8299e0`,
                    marginBottom: `20px`,
                    fontWeight: `bold`,
                    color: `black`,
                    fontSize: `22px`
                }}>
                    <p style={{textAlign: "left"}}>
                        {`${id}. ${questionName}`}
                    </p>
                </Card>
                <Card style={{background: `rgba(184, 213, 252, 0.68)`}}>
                    {
                        possibleAnswers.map(record => <StatisticsRecord color={getAnswerColor(record.answerStatus)}
                                                                        text={record.possibleAnswer}/>)
                    }

                    {/*<StatisticsRecord color={green} text={"Answer 1"}/>*/}
                    {/*<StatisticsRecord color={red} text={"Answer 1"}/>*/}
                    {/*<StatisticsRecord color={defaultColor} text={"Answer 1"}/>*/}
                    {/*<StatisticsRecord color={green} text={"Answer 1"}/>*/}
                    {/*<StatisticsRecord color={red} text={"Answer 1"}/>*/}

                    <div className="flex justify-content-end">
                        <div style={{
                            marginRight: `10px`,
                            marginTop: `10px`
                        }}>
                            <h3>
                                // TODO
                                5/10
                            </h3>
                        </div>
                    </div>
                </Card>
            </div>
        </div>
    )
}

export default StatisticsBlock