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
import Footer from "../../navbarFooter/Footer";

function FormBlock() {
    const [render, setRender] = useState(false);
    const [form, setForm]: any = useState(defaultForm);

    const [redirectToFormList, setRedirectToFormList] = useState(false);
    const [redirect404, setRedirect404] = useState(false);

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

    const showFormLimitNotification = () => {
        // @ts-ignore
        toast.current.show({
            severity:'error',
            summary: 'Invalid action',
            detail:'You have exceeded the number of attempts to pass this form',
            life: 3000
        });
    }

    const showDateLimitNotification = () => {
        // @ts-ignore
        toast.current.show({
            severity:'error',
            summary: 'Invalid action',
            detail:'You can\'t submit this form because it has expired',
            life: 3000
        });
    }

    useEffect(() => {
        axios.get(`http://localhost:8080/api/forms/${id}`, {
            headers: getAuthHeader()
        }).then(response => setForm(response.data))
          .catch(() => setRedirect404(true))
    }, [])

    const sendAnswer = (submitFormDto) => {
        axios.post(`http://localhost:8080/api/answers/new`, submitFormDto, {
            headers: getAuthHeader()
        }).then(() => setRedirectToFormList(true))
          .catch(e => {
              if (e.response.status === 423)
                  showFormLimitNotification();
              else
                  showDateLimitNotification();
          })
    }

    if (redirect404)
        return <Redirect to="/not-found"/>

    if (redirectToFormList)
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
                        <Card style={{marginTop: `30px`, marginBottom: `10px`, boxShadow: `3px 3px 12px 2px #bebebe`}}>
                            <Button style={{width: `20%`, fontSize: `20px`}} label={"Send"}
                                    onClick={() => {
                                        const formIsValid: boolean = validateUserAnswersForm(form);
                                        const submitFormDto = createSubmitFormDto(id, form);

                                        if (formIsValid)
                                            sendAnswer(submitFormDto);
                                        else
                                            showBottomRight();
                                    }}/>
                            <Toast ref={toast} position="bottom-right"/>
                        </Card>
                    </div>
                </div>
            </div>
            <Footer/>
        </div>
    )
}

export default FormBlock
