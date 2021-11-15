import React, {useState} from "react";
import {InputText} from "primereact/inputtext";
import {Button} from "primereact/button";
import {Link} from "react-router-dom";
import "../../style/registration.css"
import axios from "axios";

function Registration() {
    const singInButtonStyle: object = {
        background: '#4f1efe'
    }

    const textStyle: object = {
        color: '#6e5efe',
    }

    const [email, setEmail] = useState("");
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const h: any = {
        'Access-Control-Allow-Origin': 'http://localhost:3000',
        'Access-Control-Allow-Credentials': 'true'
    }

    const onRegistrationClicked = () => {
        console.log("CLICKED!")
        axios.post("http://localhost:8080/api/registration", {
            email: email,
            name: name,
            surname: surname,
            password: password,
            confirmPassword: confirmPassword
        }, h).then(r => console.log(r.data));
    }

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
                            <a style={textStyle} className="font-medium no-underline ml-2 cursor-pointer">
                                <Link id="login-to-registration-link" to="/login">
                                    Sign in
                                </Link>
                            </a>
                        </div>
                        <span className="text-600 font-medium line-height-3">Create an account and work with forms quickly and easily!</span>
                    </div>
                    <div>
                        <label htmlFor="email" className="block text-900 font-medium mb-2">Email</label>
                        <InputText value={email} onChange={e => setEmail(e.target.value)}
                                   id="email" type="text" className="w-full mb-3" />

                        <label htmlFor="email" className="block text-900 font-medium mb-2">Name</label>
                        <InputText value={name} onChange={e => setName(e.target.value)}
                                   type="text" className="w-full mb-3" />

                        <label htmlFor="email" className="block text-900 font-medium mb-2">Surname</label>
                        <InputText value={surname} onChange={e => setSurname(e.target.value)}
                                  type="text" className="w-full mb-3" />

                        <label htmlFor="password" className="block text-900 font-medium mb-2">Password</label>
                        <InputText value={password} onChange={e => setPassword(e.target.value)}
                                   id="password" type="password" className="blue w-full mb-3 "/>

                        <label htmlFor="password" className="block text-900 font-medium mb-2">Confirm password</label>
                        <InputText value={confirmPassword} onChange={e => setConfirmPassword(e.target.value)}
                                   id="password" type="password" className="blue w-full mb-3 "/>

                        <Button label="Create account" onClick={onRegistrationClicked}
                                style={singInButtonStyle} icon="pi pi-user" id="create-account-button" className="w-full" />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Registration