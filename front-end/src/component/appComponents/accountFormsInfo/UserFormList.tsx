import React, {useEffect, useState} from "react";
import Layout from "../../navbarFooter/Layout";
import FormInfoBlock from "./FormInfoBlock";
import {getAuthHeader} from "../../../utils/security/securityUtils";
import "../../../style/accauntFormInfo.css"
import axios from "axios";
import {useSelector} from "react-redux";

function UserFormList() {
    const [passedForms, setPassedForms] = useState([{
        answerDate: "",
        formScore: 0,
        id: 0,
        topicName: ""
    }])

    const [ownedForms, setOwnedForms] = useState([{
        expireDate: "",
        formState: "",
        id: 0,
        topicName: ""
    }])

    useEffect(() => {
        axios.get("http://localhost:8080/api/forms/available", {
            headers: getAuthHeader()
        }).then(response => setPassedForms(response.data));

        axios.get("http://localhost:8080/api/forms/owned", {
            headers: getAuthHeader()
        }).then(response => setOwnedForms(response.data));
    }, []);

    return (
        <Layout>
            <div className="all-outer-container">
                <FormInfoBlock state={true} formsPassed={passedForms} formsOwned={null}/>
                <FormInfoBlock state={false} formsPassed={null} formsOwned={ownedForms}/>
            </div>
        </Layout>
    )
}

export default UserFormList