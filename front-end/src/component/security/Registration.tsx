import React from "react";
import {InputText} from "primereact/inputtext";
import {Checkbox} from "primereact/checkbox";
import {Button} from "primereact/button";
import "../../style/registration.css"

function Registration() {
    const singInButtonStyle: object = {
        // background: "#018b4f"
        background: '#4f1efe'
    }

    const textStyle: object = {
        // color: "#05963f"
        color: '#6e5efe'
    }

    return (
        <div>
            <div className="registration flex align-items-center justify-content-center">
                <div className="registration-inner surface-card p-4 shadow-2 border-round lg:w-6">
                    <div className="text-center mb-5">
                        <div className="text-900 text-3xl font-medium mb-3">Registration</div>
                        <span className="text-600 font-medium line-height-3">Create an account and work with forms quickly and easily!</span>
                    </div>
                    <div>
                        <label htmlFor="email" className="block text-900 font-medium mb-2">Email</label>
                        <InputText id="email" type="text" className="w-full mb-3" />

                        <label htmlFor="password" className="block text-900 font-medium mb-2">Password</label>
                        <InputText id="password" type="password" className="blue w-full mb-3 "/>

                        <label htmlFor="password" className="block text-900 font-medium mb-2">Confirm password</label>
                        <InputText id="password" type="password" className="blue w-full mb-3 "/>

                        <Button label="Create account" style={singInButtonStyle} icon="pi pi-user" id="create-account-button" className="w-full" />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Registration