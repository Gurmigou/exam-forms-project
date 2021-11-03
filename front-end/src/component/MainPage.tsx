import React from "react";
import { Link } from "react-router-dom";

function MainPage() {
    return (
        <div>
            MainPage
            <Link to="/form/construct">
                <div>Create a form</div>
            </Link>
        </div>
    )
}

export default MainPage