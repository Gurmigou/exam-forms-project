import React from "react";
import { Link } from "react-router-dom";

function Navigation() {
    const style: object = {
        background: '#018b4f'
    }

    const fontStyle: object = {
        color: 'white'
    }

    return (
        <nav style={style}>
            <Link style={fontStyle} to="/">
                <div>MainPage</div>
            </Link>
            <Link style={fontStyle} to="/user/account">
                <div>Account</div>
            </Link>
            <Link style={fontStyle} to="/user/form-list">
                <div>My forms</div>
            </Link>
        </nav>
    )
}

export default Navigation