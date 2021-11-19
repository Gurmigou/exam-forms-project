import React, {useState} from "react";
import {InputText} from "primereact/inputtext";
import {Checkbox} from "primereact/checkbox";
import {Button} from "primereact/button";
import {Link, Redirect} from "react-router-dom";
import "../../style/login.css"
import axios from "axios";

function Login() {
    const [checkbox, setCheckbox] = useState(false);

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [invalidFields, setInvalidFields] = useState([{fieldName: "", reason: ""}]);

    const [redirect, setRedirect] = useState(false);

    const validateEmail = (email: string): boolean => {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    }

    const onRegistrationClicked = () => {
        const validationError: boolean = validate();

        if (!validationError) {
            setInvalidFields([{fieldName: "", reason: ""}]);

            axios.post("http://localhost:8080/api/auth", {
                email: email,
                password: password,
            }).then(r => {
                console.log(r);
                setRedirect(true);
            })
              .catch(r => setInvalidFields(old =>[...old, {fieldName: "Email", reason: r.response.data},
                  {fieldName: "Password", reason: r.response.data}]));
        }
    }

    const validate = (): boolean => {
        let error: boolean = false;

        if (email === "") {
            error = true;
            setInvalidFields(old => [...old, {fieldName: "Email", reason: "Email is required."}])
        }
        if (password === "") {
            error = true;
            setInvalidFields(old => [...old, {fieldName: "Password", reason: "Password is required."}])
        }

        if (email !== "" && !validateEmail(email)) {
            error = true;
            setInvalidFields(old => [...old, {fieldName: "Email", reason: "Invalid email address. E.g. example@email.com."}])
        }

        return error;
    }

    const isError = (fieldName: string): boolean => {
        return getErrorMessage(fieldName) !== "";
    }

    const getErrorMessage = (fieldName: string): string => {
        for (let i = 0; i < invalidFields.length; i++) {
            if (invalidFields[i].fieldName === fieldName)
                return invalidFields[i].reason;
        }
        return "";
    }

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
                        <a style={{color: '#6e5efe',}} className="font-medium no-underline ml-2 cursor-pointer">
                            <Link id="login-to-registration-link" to="/registration">
                                Create today!
                            </Link>
                        </a>
                    </div>
                    <div>
                        <div className="p-field p-fluid">
                            <label htmlFor="email" className="block text-900 font-medium mb-2">Email</label>
                            <InputText id="email" type="text" aria-describedby="username-help"
                                       className={isError("Email") ? "p-invalid p-mr-2" : "p-mr-2"}
                                       value={email} onChange={e => setEmail(e.target.value)}/>
                            <small id="username-help" className="p-error">{getErrorMessage("Email")}</small>
                        </div>
                        <div className="p-field p-fluid">
                            <label htmlFor="password" className="block text-900 font-medium mb-2">Password</label>
                            <InputText id="password" type="password" aria-describedby="username-help"
                                       className={isError("Password") ? "p-invalid p-mr-2" : "p-mr-2"}
                                       value={password} onChange={e => setPassword(e.target.value)}/>
                            <small id="username-help" className="p-error">{getErrorMessage("Password")}</small>
                        </div>
                        <div className="login-controls flex align-items-center justify-content-between mb-6">
                            <div className="flex align-items-center">
                                <Checkbox id="rememberme" onChange={e => setCheckbox(e.checked)} checked={checkbox} className="mr-2" />
                                <label htmlFor="rememberme">Remember me</label>
                            </div>
                            <Link id="remember-me-link" to={"/not-found"}>
                                <a style={{color: '#6e5efe',}} className="font-medium no-underline ml-2 text-right cursor-pointer">Forgot your password?</a>
                            </Link>
                        </div>
                        <Button label="Sign In" style={{background: '#4f1efe'}} icon="pi pi-user" className="sing-in-button w-full"
                                onClick={onRegistrationClicked}/>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login