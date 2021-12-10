import React from "react";
import {Card} from "primereact/card";
import {formatDate} from "../../../../utils/formList/userFormList";

function FormStatisticsUserBlock({userName, surname, answerDate, resultScore, formMaxResult, index}) {
    const cardBlockStyle: any = {
        width: `100%`,
        height: `70px`,
        borderRadius: 0,
        boxShadow: `none`,
        display: `flex`,
        justifyContent: `start`,
    }

    const cardContentStyle: any = {
        display: `flex`,
        flexDirection: `row`,
        justifyContent: `start`
    }

    const titleStyle: any = {
        fontSize: `18px`,
        fontWeight: `bold`
    }

    const contentStyle: any = {
        fontSize: `18px`,
        marginLeft: `10px`
    }

    return (
        <Card className="p-shadow-5" style={{
            background: `#DEEBFE`,
            marginBottom: `35px`,
            boxShadow: `5px 5px 12px 3px #dcdcdc`
        }}>
            <div className="flex justify-content-center">
                <p style={{fontSize: `22px`, fontWeight: `bold`}}>{`User ${index}`}</p>
            </div>
            <Card style={cardBlockStyle}>
                <div style={cardContentStyle}>
                    <p style={titleStyle}>{`Name: `}</p>
                    <p style={contentStyle}>{userName}</p>
                </div>

            </Card>
            <Card style={cardBlockStyle}>
                <div style={cardContentStyle}>
                    <p style={titleStyle}>{`Surname: `}</p>
                    <p style={contentStyle}>{surname}</p>
                </div>
            </Card>
            <Card style={cardBlockStyle}>
                <div style={cardContentStyle}>
                    <p style={titleStyle}>{`Answer date: `}</p>
                    <p style={contentStyle}>{formatDate(answerDate)}</p>
                </div>
            </Card>
            <Card style={cardBlockStyle}>
                <div style={cardContentStyle}>
                    <p style={titleStyle}>{`Grade: `}</p>
                    <p style={contentStyle}>{`${resultScore}/${formMaxResult}`}</p>
                </div>
            </Card>
        </Card>
    )
}

export default FormStatisticsUserBlock