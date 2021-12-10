import React, {useEffect, useRef, useState} from "react";
import FormTitle from "./FormTitle";
import QuestionBlock from "./QuestionBlock";
import {defaultForm} from "../../../utils/form/formUtils";
import { useParams } from 'react-router-dom';
import axios from "axios";
import {getAuthHeader} from "../../../utils/security/securityUtils";

function FormBlock() {
    const [form, setForm] = useState(defaultForm);
    const [redirect, setRedirect] = useState(false);
    const { id } = useParams();

    useEffect(() => {
        axios.get(`http://localhost:8080/forms/${id}`, {
            headers: getAuthHeader()
        }).then(response => setForm(response.data))
    }, [])

    return (
        <div>
            <div style={{background: `rgba(206, 226, 253, 0.68)`}} className="all-outer-container">
                <div className="form-info-block">
                    <div className="form-info-block-content">
                        <FormTitle title={form.topicName}/>
                        {
                            form.questionDtoList.map((value, index) =>
                                <QuestionBlock index={index} question={value}/>)
                        }
                    </div>
                </div>
            </div>
        </div>
    )
}

export default FormBlock
