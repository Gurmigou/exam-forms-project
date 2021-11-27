import React, {useEffect, useState} from "react";
import Layout from "../../navbarFooter/Layout";
import {Button} from "primereact/button";
import {InputText} from "primereact/inputtext";
import "../../../style/accauntSettings.css"
import axios from "axios";
import {updateProfile} from "../../../utils/account/accountUtils";
import {getAuthHeader} from "../../../utils/security/securityUtils";

function AccountSettings() {
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");

    const [newName, setNewName] = useState("");
    const [newSurname, setNewSurname] = useState("");
    const [oldPassword, setOldPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");

    const [oldPasswordError, setOldPasswordError] = useState(false);
    const [oldPasswordErrorMessage, setOldPasswordErrorMessage] = useState("");
    const [newPasswordError, setNewPasswordError] = useState(false);
    const [newPasswordErrorMessage, setNewPasswordErrorMessage] = useState("");

    useEffect(() => {
        axios.get("http://localhost:8080/api/user", {
            headers: getAuthHeader()
        }).then(response => {
            setName(response.data.name);
            setSurname(response.data.surname);
        });
    }, []);

    useEffect(() => {
        console.log("Old " + oldPasswordError);
        console.log("New " + newPasswordError);
    }, [oldPasswordError, newPasswordError])

    return (
        <Layout>
            <div className="all-outer-container">
                <div className="account-settings-container">
                    <div className="account-settings-content">
                        <div className="current-account-info">
                            <h3 id="current-account-info-text">
                                Welcome, {name + " " + surname}
                            </h3>
                        </div>
                        <div className="edit-account-block">
                            <div className="edit-value">
                                <div className="edit-container-left">
                                    <div className="edit-inner-name">
                                        Change your name
                                    </div>
                                </div>
                                <div className="edit-container-right">
                                    <InputText className="edit-input" value={newName}
                                               onChange={e => setNewName(e.target.value)}/>
                                </div>
                            </div>
                            <div className="edit-value">
                                <div className="edit-container-left">
                                    <div className="edit-inner-name">
                                        Change your surname
                                    </div>
                                </div>
                                <div className="edit-container-right">
                                    <InputText className="edit-input" value={newSurname}
                                               onChange={e => setNewSurname(e.target.value)}/>
                                </div>
                            </div>
                            <br/>
                            <h2 id="change-password-label">Change password</h2>
                            <div className="edit-value">
                                <div className="edit-container-left">
                                    <div className="edit-inner-name">
                                        Enter old password
                                    </div>
                                </div>
                                <div className="edit-container-right">
                                    <div className="p-field p-fluid edit-container-with-error">
                                        <InputText id="password" type="password"
                                                   aria-describedby="username-help"
                                                   className={"edit-input " + (oldPasswordError ?
                                                       "p-invalid p-mr-2" : "p-mr-2")}
                                                   value={oldPassword}
                                                   onChange={e => setOldPassword(e.target.value)}/>
                                        <small id="username-help"
                                        className="p-error">{oldPasswordErrorMessage}</small>
                                    </div>
                                </div>
                            </div>
                            <div className="edit-value">
                                <div className="edit-container-left">
                                    <div className="edit-inner-name">
                                        Enter new password
                                    </div>
                                </div>
                                <div className="edit-container-right">
                                    <div className="p-field p-fluid edit-container-with-error">
                                        <InputText id="password" type="password"
                                                   aria-describedby="username-help"
                                                   className={"edit-input " + (newPasswordError ?
                                                       "p-invalid p-mr-2" : "p-mr-2")}
                                                   value={newPassword}
                                                   onChange={e => setNewPassword(e.target.value)}/>
                                        <small id="username-help"
                                               className="p-error">{newPasswordErrorMessage}</small>
                                    </div>
                                </div>
                            </div>
                            <div className="button-container">
                                <Button id="button-apply" label="Apply" onClick={() => updateProfile(
                                    newName, newSurname, oldPassword, newPassword,
                                    setName, setSurname, setOldPasswordError, setOldPasswordErrorMessage,
                                    setNewPasswordError, setNewPasswordErrorMessage)}/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default AccountSettings