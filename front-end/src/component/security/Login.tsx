import React, {useEffect, useState} from "react";
import {InputText} from "primereact/inputtext";
import {Button} from "primereact/button";
import {Link, Redirect} from "react-router-dom";
import { useDispatch } from "react-redux";
import {getErrorMessage, isError, login, user} from "../../utils/security/securityUtils";
import "../../style/login.css"

function Login() {
    // const [checkbox, setCheckbox] = useState(false);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [invalidFields, setInvalidFields] = useState([{fieldName: "", reason: ""}]);
    const [redirect, setRedirect] = useState(false);
    const dispatch = useDispatch();

    if (redirect)
        return <Redirect to="/user/form-list"/>

    return (
        <div>
            <div className="login flex align-items-center justify-content-center">
                <div className="login-inner surface-card p-4 shadow-2 border-round lg:w-6">
                    <div className="text-center mb-5">
                        <div className="text-900 text-5xl font-bold mb-3">
                            <Link id="route-to-main-text" to="/">
                                <i id="route-to-main-icon" className="far fa-file-alt"/>
                                <a >Edulse</a>
                            </Link>
                        </div>
                        <div className="text-900 text-3xl font-medium mb-3">Welcome Back</div>
                        <span className="text-600 font-medium line-height-3">Don't have an account?</span>
                        <a style={{color: '#6e5efe'}} className="font-medium no-underline ml-2 cursor-pointer">
                            <Link id="login-to-registration-link" to="/registration">
                                Create today!
                            </Link>
                        </a>
                    </div>
                    <div>
                        <div className="p-field p-fluid">
                            <label htmlFor="email" className="block text-900 font-medium mb-2">Email</label>
                            <InputText id="email" type="text" aria-describedby="username-help"
                                       className={isError(invalidFields,"Email") ? "p-invalid p-mr-2" : "p-mr-2"}
                                       value={email} onChange={e => setEmail(e.target.value)}/>
                            <small id="username-help" className="p-error">{getErrorMessage(invalidFields,"Email")}</small>
                        </div>

                        <div className="p-field p-fluid">
                            <label htmlFor="password" className="block text-900 font-medium mb-2">Password</label>
                            <InputText id="password" type="password" aria-describedby="username-help"
                                       className={isError(invalidFields, "Password") ? "p-invalid p-mr-2" : "p-mr-2"}
                                       value={password} onChange={e => setPassword(e.target.value)}/>
                            <small id="username-help" className="p-error">{getErrorMessage(invalidFields,"Password")}</small>
                        </div>

                        {/*<div className="login-controls flex align-items-center justify-content-between mb-6">*/}
                        {/*    <div className="flex align-items-center">*/}
                        {/*        <Checkbox id="rememberme" onChange={e => setCheckbox(e.checked)} checked={checkbox} className="mr-2" />*/}
                        {/*        <label htmlFor="rememberme">Remember me</label>*/}
                        {/*    </div>*/}
                        {/*    <Link id="remember-me-link" to={"/not-found"}>*/}
                        {/*        <a style={{color: '#6e5efe'}} className="font-medium no-underline ml-2 text-right cursor-pointer">Forgot your password?</a>*/}
                        {/*    </Link>*/}
                        {/*</div>*/}

                        <Button label="Sign In" style={{background: '#4f1efe'}} icon="pi pi-user" className="sing-in-button w-full" id='sign-button'
                                onClick={() => login(user(email, password), dispatch, setRedirect, setInvalidFields)}/>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login