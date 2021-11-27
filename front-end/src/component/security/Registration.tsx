import React, {useState} from "react";
import {InputText} from "primereact/inputtext";
import {Button} from "primereact/button";
import {Link, Redirect} from "react-router-dom";
import "../../style/registration.css"
import {getErrorMessage, isError, register, userSend, validateRegistration} from "../../utils/security/securityUtils";

function Registration() {
    const [email, setEmail] = useState("");
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const [invalidFields, setInvalidFields] = useState([{fieldName: "", reason: ""}]);
    const [redirect, setRedirect] = useState(false);

    if (redirect)
        return <Redirect to="/login"/>

    return (
        <div>
            <div className="registration flex align-items-center justify-content-center">
                <div className="registration-inner surface-card p-4 shadow-2 border-round lg:w-6">
                    <div className="text-900 text-5xl font-bold mb-3">
                        <Link id="route-to-main-text" to="/">
                            <i id="route-to-main-icon" className="far fa-file-alt"/>
                            <a >Edulse</a>
                        </Link>
                    </div>
                    <div className="text-center mb-5">
                        <div className="text-900 text-3xl font-medium mb-3">Registration</div>
                        <div>
                            <span className="text-600 font-medium line-height-3">Already have an account?</span>
                            <a style={{color: '#6e5efe',}} className="font-medium no-underline ml-2 cursor-pointer">
                                <Link id="login-to-registration-link" to="/login">
                                    Sign in
                                </Link>
                            </a>
                        </div>
                        <span className="text-600 font-medium line-height-3">Create an account and work with forms quickly and easily!</span>
                    </div>
                    <div>
                        <div className="p-field p-fluid">
                            <label htmlFor="email" className="block text-900 font-medium mb-2">Email</label>
                            <InputText id="email" type="email" aria-describedby="username-help"
                                       className={isError(invalidFields, "Email") ? "p-invalid p-mr-2" : "p-mr-2"}
                                       value={email} onChange={e => setEmail(e.target.value)}/>
                            <small id="username-help" className="p-error">{getErrorMessage(invalidFields, "Email")}</small>
                        </div>

                        <div className="p-field p-fluid">
                            <label className="block text-900 font-medium mb-2">Name</label>
                            <InputText type="text" aria-describedby="username-help"
                                       className={isError(invalidFields, "Name") ? "p-invalid p-mr-2" : "p-mr-2"}
                                       value={name} onChange={e => setName(e.target.value)}/>
                            <small id="username-help" className="p-error">{getErrorMessage(invalidFields, "Name")}</small>
                        </div>
                        <div className="p-field p-fluid">
                            <label className="block text-900 font-medium mb-2">Surname</label>
                            <InputText type="text" aria-describedby="username-help"
                                       className={isError(invalidFields, "Surname") ? "p-invalid p-mr-2" : "p-mr-2"}
                                       value={surname} onChange={e => setSurname(e.target.value)}/>
                            <small id="username-help" className="p-error">{getErrorMessage(invalidFields, "Surname")}</small>
                        </div>
                        <div className="p-field p-fluid">
                            <label htmlFor="password" className="block text-900 font-medium mb-2">Password</label>
                            <InputText id="password" type="password" aria-describedby="username-help"
                                       className={isError(invalidFields, "Password") ? "p-invalid p-mr-2" : "p-mr-2"}
                                       value={password} onChange={e => setPassword(e.target.value)}/>
                            <small id="username-help" className="p-error">{getErrorMessage(invalidFields, "Password")}</small>
                        </div>
                        <div className="p-field p-fluid">
                            <label htmlFor="password" className="block text-900 font-medium mb-2">Confirm password</label>
                            <InputText id="password" type="password" aria-describedby="username-help"
                                       className={isError(invalidFields, "Confirm password") ? "p-invalid p-mr-2" : "p-mr-2"}
                                       value={confirmPassword} onChange={e => setConfirmPassword(e.target.value)}/>
                            <small id="username-help" className="p-error">{getErrorMessage(invalidFields, "Confirm password")}</small>
                        </div>
                        <Button label="Create account" onClick={() => register(
                            validateRegistration(email, name, surname, password, confirmPassword, setInvalidFields), setInvalidFields,
                            setRedirect, userSend(email, name, surname, password, confirmPassword))}
                                style={{background: '#4f1efe'}} icon="pi pi-user" id="create-account-button" className="w-full" />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Registration