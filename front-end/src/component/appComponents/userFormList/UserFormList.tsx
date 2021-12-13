import React, {useEffect, useState} from "react";
import Layout from "../../navbarFooter/Layout";
import FormInfoBlock from "./FormInfoBlock";
import {getAuthHeader} from "../../../utils/security/securityUtils";
import "../../../style/accauntFormInfo.css"
import axios from "axios";

function UserFormList() {
    const [passedForms, setPassedForms] = useState([])

    const [ownedForms, setOwnedForms] = useState([])

    useEffect(() => {
        // axios.get("http://localhost:8080/api/forms/available", {
        //     headers: getAuthHeader()
        // }).then(response => {
        //     console.log("available")
        //     console.log(response)
        //     setPassedForms(response.data)
        // });

        axios.get("http://localhost:8080/api/forms/owned", {
            headers: getAuthHeader()
        }).then(response => {
            console.log("owned")
            console.log(response)
            setOwnedForms(response.data)
        });
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