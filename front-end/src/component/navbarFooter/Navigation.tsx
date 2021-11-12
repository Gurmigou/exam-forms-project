import React, {useState} from "react";
import { Link } from "react-router-dom";
import {NavItems} from "./NavItems";
import "../../style/navbar.css"

function Navigation() {
    const [clicked, setClicked] = useState(false);

    return (
        <nav className="navbar-items">
            <Link id="nav-link-box" to="/">
                <h1 className="navbar-logo">Edulse</h1>
            </Link>

            <div className="menu-icon" onClick={() => setClicked(!clicked)}>
                <i className={clicked ? 'fas fa-times' : 'fas fa-bars'}/>
            </div>

            <ul className={clicked ? 'nav-menu active' : 'nav-menu'}>
                {NavItems.map((item, index) => {
                    return (
                        <li>
                            <Link id="nav-link-box" to={item.url}>
                                <a className={item.cName}>{item.title}</a>
                            </Link>
                        </li>
                    )
                })}
            </ul>
        </nav>
    )
}

export default Navigation