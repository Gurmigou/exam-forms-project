import React from "react";
import {Button} from "primereact/button";
import { Link } from "react-router-dom";

function MainPageComponent(props: {title: string, index: number, buttonText: string, onClickRoute: string}) {
    return (
        <div id={"main-page-component-" + props.index} className="main-page-component">
            <div className="main-page-component-title">
                <h1>{props.title}</h1>
            </div>
            <div className="main-page-component-button-block">
                <Link className="main-page-component-button" to={props.onClickRoute}>
                    <Button id="main-page-component-button-item" label={props.buttonText}/>
                </Link>
            </div>
        </div>
    )
}

export default MainPageComponent;