import React, {useEffect, useRef, useState} from "react";
import FormTitle from "./FormTitle";
import QuestionBlock from "./QuestionBlock";
import {createSubmitFormDto, defaultForm, validateUserAnswersForm} from "../../../utils/form/formUtils";
import { useParams } from 'react-router-dom';
import axios from "axios";
import {getAuthHeader} from "../../../utils/security/securityUtils";
import {Button} from "primereact/button";
import {Toast} from "primereact/toast";
import {Card} from "primereact/card";
import {Redirect} from "react-router-dom";

function FormBlock() {
    const [render, setRender] = useState(false);
    const [form, setForm]: any = useState(defaultForm);

    const [redirect, setRedirect] = useState(false);
    const { id } = useParams();

    const toast = useRef(null);

    const showBottomRight = () => {
        // @ts-ignore
        toast.current.show({
            severity:'error',
            summary: 'Invalid input',
            detail:'Make sure that all fields are filled in',
            life: 3000
        });
    }

    useEffect(() => {
        console.log(id)

        axios.get(`http://localhost:8080/forms/${id}`, {
            headers: getAuthHeader()
        }).then(response => setForm(response.data))
    }, [])

    const sendAnswer = (submitFormDto) => {
        axios.post(`http://localhost:8080/answers/new`, submitFormDto, {
            headers: getAuthHeader()
        }).then(() => setRedirect(true));
    }

    if (redirect)
        return <Redirect to="/user/form-list"/>

    return (
        <div>
            <div style={{background: `rgba(206, 226, 253, 0.68)`}} className="all-outer-container">
                <div className="form-info-block">
                    <div className="form-info-block-content">
                        <FormTitle title={form.topicName}/>
                        {
                            form.questionDtoList.map((value, index) =>
                                <QuestionBlock index={index}
                                               question={value}
                                               setRender={setRender}/>)
                        }
                        <Card style={{marginTop: `30px`, boxShadow: `3px 3px 12px 2px #bebebe`}}>
                            <Button style={{width: `20%`, fontSize: `20px`}} label={"Send"}
                                    onClick={() => {
                                        const formIsValid: boolean = validateUserAnswersForm(form);
                                        const submitFormDto = createSubmitFormDto(id, form);

                                        console.log(submitFormDto);

                                        if (formIsValid)
                                            sendAnswer(submitFormDto);
                                        else
                                            showBottomRight();
                                    }}/>
                            <Toast ref={toast} position="bottom-right" />
                        </Card>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default FormBlock
