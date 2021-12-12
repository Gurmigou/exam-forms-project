import React, {useEffect, useRef, useState} from "react";
import Layout from "../../../navbarFooter/Layout";
import QuestionBuilderBlock from "../questionBuilder/QuestionBuilderBlock";
import FormTitleBuilderBlock from "./FormTitleBuilderBlock";
import {createNewFormDto, defaultQuestionList, validateNewFormDto}
    from "../../../../utils/formConstructor/formConstructorUtils";
import {Button} from "primereact/button";
import {Card} from "primereact/card";
import {Toast} from "primereact/toast";
import {Redirect} from "react-router-dom";
import axios from "axios";
import {getAuthHeader} from "../../../../utils/security/securityUtils";

function FormBuilder() {
    const [formTitle, setFormTitle] = useState("");
    const [maxAttempts, setMaxAttempts] = useState("");
    const [expiresInDays, setExpireInDays] = useState("");
    const [questionList, setQuestionList] = useState(defaultQuestionList);

    const toast = useRef(null);
    const [redirect, setRedirect] = useState(false);

    const showBottomRight = () => {
        // @ts-ignore
        toast.current.show({
            severity:'error',
            summary: 'Invalid input',
            detail:'Make sure that all fields are filled in',
            life: 3000
        });
    }

    const sendNewFormDto = (newFormDto) => {
        axios.post("http://localhost:8080/api/forms/new", newFormDto, {
            headers: getAuthHeader()
        }).then(() => setRedirect(true));
    }

    useEffect(() => {
        console.log(questionList);
    }, [questionList])

    if (redirect)
        return <Redirect to="/user/form-list"/>

    return (
        <Layout>
            <div style={{background: `rgba(206, 226, 253, 0.68)`}} className="all-outer-container">
                <div className="form-info-block">
                    <div className="form-info-block-content">
                        <FormTitleBuilderBlock formTitle={formTitle} setFormTitle={setFormTitle}
                                               maxAttempts={maxAttempts} setMaxAttempts={setMaxAttempts}
                                               expiresInDays={expiresInDays} setExpireInDays={setExpireInDays}/>
                        {
                            questionList.map((value, index) =>
                                <QuestionBuilderBlock questionList={questionList} setQuestionList={setQuestionList}
                                                      questionType={value.questionType} questionIndex={index}/>)
                        }
                        <Card style={{marginTop: `30px`}}>
                            <Button style={{width: `20%`, fontSize: `20px`}} label={"Send"}
                                    onClick={() => {
                                        const newForm = createNewFormDto(formTitle, maxAttempts,
                                            expiresInDays, questionList);
                                        const formIsValid: boolean = validateNewFormDto(newForm);
                                        console.log(newForm);

                                        if (formIsValid)
                                            sendNewFormDto(newForm);
                                        else
                                            showBottomRight();
                                    }}/>
                            <Toast ref={toast} position="bottom-right" />
                        </Card>
                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default FormBuilder