import {applyMiddleware, createStore} from "redux";
import {composeWithDevTools } from 'redux-devtools-extension'
import thunk from "redux-thunk";

const SET_USER: string = "SET_USER";
const LOG_OUT: string = "LOG_OUT";

const defaultState: object = {
    currentUser: {},
    isAuth: false
}

export const reducer = (state = defaultState, action): object => {
    switch (action.type) {
        case SET_USER:
            return {
                ...state,
                currentUser: action.payload,
                isAuth: true
            }
        case LOG_OUT:
            // remove jwt token from the local storage
            localStorage.removeItem("token");
            return {
                ...state,
                currentUser: {},
                isAuth: false
            }
        default:
            return state;
    }
}

export const setUser = (user: {email: string}) => ({type: SET_USER, payload: user});
export const logout = () => ({type: LOG_OUT});

export const store = createStore(reducer, composeWithDevTools(applyMiddleware(thunk)));