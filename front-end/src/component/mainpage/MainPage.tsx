import React, {useEffect} from "react";
import "../../style/mainPage.css"
import MainPageComponent from "./MainPageComponent";
import { Link } from "react-router-dom";
import {logout} from "../../utils/redux/reduxUtils";
import {useDispatch, useSelector} from "react-redux";

function MainPage() {
    const user = useSelector((state: any) => state)
    const dispatch = useDispatch()

    useEffect(() => {
        console.log(user);
    }, [])

    return (
        <div className="main-page">
            <div className="main-page-nav">
                <Link id="main-page-nav-link-1" className="main-page-nav-link" to="/">
                    <h1 className="">Edulse</h1>
                </Link>
                {
                    !user.isAuth ?
                        <Link id="main-page-nav-link-2" className="main-page-nav-link" to="/login">
                            <h1 className="">Sign in</h1>
                        </Link>
                        :
                        <Link id="main-page-nav-link-2" className="main-page-nav-link" to="/">
                            <h1 onClick={() => dispatch(logout())} className="">Log out</h1>
                        </Link>
                }
            </div>
            <div className="main-page-components-box">
                <div className="main-page-components-box-inner">
                    <MainPageComponent title={"New form"} index={1} buttonText={"Create"} onClickRoute={"/form/construct"}/>
                    <MainPageComponent title={"Form list"} index={2} buttonText={"View"} onClickRoute={"/user/form-list"}/>
                </div>
            </div>
        </div>
    )
}

export default MainPage