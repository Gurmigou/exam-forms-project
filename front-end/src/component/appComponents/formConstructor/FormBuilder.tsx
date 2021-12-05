import React from "react";
import Layout from "../../navbarFooter/Layout";
import FormBuilderBlock from "./FormBuilderBlock";

function FormBuilder() {
    return (
        <Layout>
            <div className="all-outer-container">
                <div className="form-info-block">
                    <div className="form-info-block-content">
                        <FormBuilderBlock/>
                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default FormBuilder