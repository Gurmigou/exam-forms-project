import React from "react";
import Layout from "../../../navbarFooter/Layout";
import QuestionBuilderBlock from "../questionBuilder/QuestionBuilderBlock";
import FormTitleBuilderBlock from "./FormTitleBuilderBlock";

function FormBuilder() {

    return (
        <Layout>
            <div className="all-outer-container">
                <div className="form-info-block">
                    <div className="form-info-block-content">
                        <FormTitleBuilderBlock/>
                        <QuestionBuilderBlock/>
                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default FormBuilder