import axios from "axios";
import {getAuthHeader} from "../security/securityUtils";

const clearErrors = (setErrorOld, setMessageOld, setErrorNew, setMessageNew) => {
    setErrorOld(false);
    setMessageOld("");
    setErrorNew(false);
    setMessageNew("");
}

export const updateProfile = (newName: string, newSurname: string, oldPassword: string, newPassword: string,
                              setName, setSurname, setErrorOld, setMessageOld, setErrorNew, setMessageNew) => {
    const valid = validateNewPassword(oldPassword, newPassword, setErrorOld, setMessageOld, setErrorNew, setMessageNew);

    console.log("Valid: " + valid);

    if (newName !== "" || newSurname !== "" || (oldPassword !== "" && newPassword !== "")) {

        if (valid) {
            clearErrors(setErrorOld, setMessageOld, setErrorNew, setMessageNew);

            axios.put("http://localhost:8080/api/user", {
                newName: newName ? newName : null,
                newSurname : newSurname ? newSurname : null,
                oldPassword : oldPassword ? oldPassword : null,
                newPassword : newPassword ? newPassword : null
            },{
                headers: getAuthHeader()
            }).then(response => {
                setName(response.data.name);
                setSurname(response.data.surname);
            }).catch(e => {
                setErrorOld(true);
                setMessageOld(e.response.data);
            })
        }
    }
}

const validateNewPassword = (oldPassword: string, newPassword: string,
                             setErrorOld, setMessageOld,
                             serErrorNew, setMessageNew): boolean =>
{
    console.log(oldPassword);
    console.log(newPassword)


    let error = false;
    if (newPassword !== "" && oldPassword === "") {
        setErrorOld(true);
        setMessageOld("Your current password is required to change password.");
        error = true;
    }
    if (newPassword !== "" && oldPassword !== "") {
        if (newPassword.length < 8) {
            setMessageNew(true);
            setMessageNew("Password must be at least 8 characters.");
            error = true;
        }
        if (oldPassword.length < 8) {
            setMessageOld(true);
            setMessageOld("Password must be at least 8 characters.");
            error = true;
        }
    }
    return !error;
}