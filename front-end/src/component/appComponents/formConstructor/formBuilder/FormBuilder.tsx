import React, {useEffect, useState} from "react";
import Layout from "../../../navbarFooter/Layout";
import QuestionBuilderBlock from "../questionBuilder/QuestionBuilderBlock";
import FormTitleBuilderBlock from "./FormTitleBuilderBlock";
import {defaultQuestionList} from "../../../../utils/formConstructor/formConstructorUtils";

function FormBuilder() {
    const [formTitle, setFormTitle] = useState("");
    const [maxAttempts, setMaxAttempts] = useState("");
    const [expiresInDays, setExpireInDays] = useState("");
    const [questionList, setQuestionList] = useState(defaultQuestionList);

    useEffect(() => {
        console.log(questionList);
    }, [questionList])

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
                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default FormBuilder