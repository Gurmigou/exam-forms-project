import React, {useState} from "react";
import Layout from "../../navbarFooter/Layout";
import "../../../style/formEditor.css"
import {InputText} from "primereact/inputtext";
import {Card} from "primereact/card";
import {InputNumber} from "primereact/inputnumber";
import QuestionEditor from "./QuestionEditor";

function FormEditor() {
    const [topic, setTopic] = useState('');
    const [expire, setExpire] = useState(10);
    const [attempts, setAttempts] = useState(10);
    return (
        <Layout>
            <div className="all-outer-container">
                <div className="form-container">
                    <div className="form-content">
                        <div className="form-header">
                            <div className="input-topic-field">
                                <InputText value={topic} onChange={(e) =>
                                    setTopic(e.target.value)} placeholder="Topic name"/>
                                <InputNumber inputId="expiry" value={expire} onValueChange={(e) =>
                                    setExpire(e.value)} prefix="Expires in " suffix=" days"/>
                                <InputNumber inputId="attempts" value={attempts} onValueChange={(e) =>
                                    setExpire(e.value)} prefix="Maximum attempts "/>
                            </div>
                        </div>
                        <div className="form-body">
                            <Card style={{width: '77.5rem', marginBottom: '2em'}}>
                                <QuestionEditor/>
                            </Card>
                        </div>
                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default FormEditor