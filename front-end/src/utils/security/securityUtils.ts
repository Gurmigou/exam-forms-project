import axios from "axios";
import {setUser} from "../redux/reduxUtils";

export const validateEmail = (email: string): boolean => {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

export const isError = (invalidFields: {fieldName: string, reason: string}[], fieldName: string): boolean => {
    console.log("IS ERROR: " + getErrorMessage(invalidFields, fieldName) !== "");
    return getErrorMessage(invalidFields, fieldName) !== "";
}

export const getErrorMessage = (invalidFields: {fieldName: string, reason: string}[], fieldName: string): string => {
    for (let i = 0; i < invalidFields.length; i++) {
        console.log(i + ") " + invalidFields[i].fieldName);
        if (invalidFields[i].fieldName === fieldName)
            return invalidFields[i].reason;
    }
    return "";
}

export const user = (email: string, password: string): {email: string, password: string} => {
    return {
        email: email,
        password: password
    }
}

export const userSend = (email: string, name: string, surname: string, password: string, confirmPassword: string):
    {email: string, name: string, surname: string, password: string, confirmPassword: string} =>
{
    return {
        email: email,
        name: name,
        surname: surname,
        password: password,
        confirmPassword: confirmPassword
    }
}

// export const validateLogin = (email: string, password: string, setInvalidFields): boolean => {
//     let isValid: boolean = true;
//
//     if (email === "") {
//         console.log("1")
//         isValid = false;
//         setInvalidFields(old => [...old, {fieldName: "Email", reason: "Email is required."}])
//     }
//     if (password === "") {
//         console.log("2")
//         isValid = false;
//         setInvalidFields(old => [...old, {fieldName: "Password", reason: "Password is required."}])
//     }
//
//     if (email !== "" && !validateEmail(email)) {
//         console.log("3")
//         isValid = false;
//         setInvalidFields(old => [...old, {fieldName: "Email", reason: "Invalid email address. E.g. example@email.com."}])
//     }
//
//     return isValid;
// }

export const validateRegistration = (email: string, name: string, surname: string, password: string, confirmPassword: string, setInvalidFields): boolean => {
    let isValid: boolean = true;

    const arr = [
        {name: "Email", value: email},
        {name: "Name", value: name},
        {name: "Surname", value: surname},
        {name: "Password", value: password},
        {name: "Confirm password", value: confirmPassword}
    ];

    arr.forEach(e => {
        if (e.value === "") {
            isValid = false;
            setInvalidFields(old => [...old, {fieldName: e.name, reason: e.name + " is required."}])
        }
    });

    if (email !== "" && !validateEmail(email)) {
        isValid = false;
        setInvalidFields(old => [...old, {fieldName: "Email", reason: "Invalid email address. E.g. example@email.com."}])
    }

    if (password !== "" && confirmPassword !== "") {
        if (password !== confirmPassword) {
            isValid = false;
            setInvalidFields(old => [...old, {fieldName: "Confirm password", reason: "Password and confirm password are different."}])

        }
        else if (password.length < 8) {
            isValid = false;
            setInvalidFields(old => [...old, {fieldName: "Password", reason: "Password must be at least 8 characters."}])
        }
    }

    return isValid;
}

// export const login = (isValid: boolean, setInvalidFields, setRedirect, user: {email: string, password: string}) => {
//     return async dispatch => {
//         // if (isValid) {
//         //     setInvalidFields([{fieldName: "", reason: ""}]);
//         //
//         //     let response;
//         //     try {
//         //         console.log("USER:" + user.email + " " + user.password);
//         //
//         //         response = await axios.post("http://localhost:8080/api/auth", user);
//         //
//         //         dispatch(setUser({
//         //             email: user.email,
//         //             password: user.password,
//         //             token: response.data.jwtToken
//         //         }));
//         //
//         //         console.log(response);
//         //
//         //         // localStorage.setItem("token:", response.data.jwtToken);
//         //
//         //         setRedirect(true);
//         //     } catch (e) {
//         //         setInvalidFields(old => [...old, {fieldName: "Email", reason: response.response.data},
//         //             {fieldName: "Password", reason: response.response.data}])
//         //     }
//         // }
//
//         if (isValid) {
//             axios.post("http://localhost:8080/api/auth", user)
//                  .then(response => {
//                      dispatch(setUser({
//                          email: user.email,
//                          password: user.password,
//                          token: response.data.jwtToken
//                      }));
//                      setRedirect(true);
//             })
//                 .catch(r => setInvalidFields(old =>[...old, {fieldName: "Email", reason: r.response.data},
//                     {fieldName: "Password", reason: r.response.data}]));
//         } else {
//             setInvalidFields([{fieldName: "", reason: ""}]);
//         }
//     }
// }


export const onLoginClicked = (user, dispatch, setRedirect, setInvalidFields) => {
    const validationError: boolean = validate(user.email, user.password, setInvalidFields);

    if (!validationError) {
        setInvalidFields([{fieldName: "", reason: ""}]);

        axios.post("http://localhost:8080/api/auth", user)
             .then(response => {
            dispatch(setUser({
                email: user.email,
                password: user.password,
                token: response.data.jwtToken
            }));
            setRedirect(true);
        }).catch(r => setInvalidFields(old =>[...old, {fieldName: "Email", reason: r.response.data},
            {fieldName: "Password", reason: r.response.data}]));
    }
}

export const validate = (email: string, password: string, setInvalidFields): boolean => {
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



export const register = (isValid: boolean, setInvalidFields, setRedirect, user:
    {email: string, name: string, surname: string, password: string, confirmPassword: string}): void =>
{
    if (isValid) {
        setInvalidFields([{fieldName: "", reason: ""}]);

        axios.post("http://localhost:8080/api/registration", user)
             .then(() => setRedirect(true))
             .catch(r => setInvalidFields(old =>[...old, {fieldName: "Email", reason: r.response.data}]));
    }
}

export const getAuthHeader = (token: string): object => {
    return {
        Authorization: `Bearer ${token}`
    }
}