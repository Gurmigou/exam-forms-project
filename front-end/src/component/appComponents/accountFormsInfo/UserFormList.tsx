import React, {useEffect, useState} from "react";
import Layout from "../../navbarFooter/Layout";
import FormInfoBlock from "./FormInfoBlock";
import {getAuthHeader} from "../../../utils/security/securityUtils";
import "../../../style/accauntFormInfo.css"
import axios from "axios";

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

    const arr1 = [
        {
            answerDate: "2021-11-26T14:08:59.124Z",
            formScore: 20,
            id: 1,
            topicName: "Math test"
        },
        {
            answerDate: "2021-11-26T14:08:59.124Z",
            formScore: 95,
            id: 2,
            topicName: "Algorithms and data structures"
        },
        {
            answerDate: "2021-11-26T14:08:59.124Z",
            formScore: 65,
            id: 3,
            topicName: "How to become rich and start investing test"
        },
        {
            answerDate: "2021-11-26T14:08:59.124Z",
            formScore: 95,
            id: 2,
            topicName: "Algorithms and data structures"
        },
        {
            answerDate: "2021-11-26T14:08:59.124Z",
            formScore: 95,
            id: 2,
            topicName: "Algorithms and data structures"
        },
        {
            answerDate: "2021-11-26T14:08:59.124Z",
            formScore: 95,
            id: 2,
            topicName: "Algorithms and data structures"
        },
        {
            answerDate: "2021-11-26T14:08:59.124Z",
            formScore: 95,
            id: 2,
            topicName: "Algorithms and data structures"
        },
        {
            answerDate: "2021-11-26T14:08:59.124Z",
            formScore: 95,
            id: 2,
            topicName: "Algorithms and data structures"
        },
        {
            answerDate: "2021-11-26T14:08:59.124Z",
            formScore: 95,
            id: 2,
            topicName: "Algorithms and data structures"
        },
        {
            answerDate: "2021-11-26T14:08:59.124Z",
            formScore: 95,
            id: 2,
            topicName: "Algorithms and data structures"
        },
        {
            answerDate: "2021-11-26T14:08:59.124Z",
            formScore: 95,
            id: 2,
            topicName: "Algorithms and data structures"
        }
    ]

    const arr2 = [
        {
            expireDate: "2021-11-30T14:08:59.124Z",
            formState: "PASSED",
            id: 1,
            topicName: "Nature"
        },
        {
            expireDate: "2021-12-01T14:08:59.124Z",
            formState: "CLOSED",
            id: 2,
            topicName: "English module 1 test"
        }
    ]

    useEffect(() => {
        axios.get("http://localhost:8080/api/forms/available", {
            headers: getAuthHeader()
        }).then(response => setPassedForms(arr1)/*setPassedForms(response.data)*/);

        axios.get("http://localhost:8080/api/forms/owned", {
            headers: getAuthHeader()
        }).then(response => setOwnedForms(arr2)/*setOwnedForms(response.data)*/);
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