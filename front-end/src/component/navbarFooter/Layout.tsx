import React from "react";
import Navigation from "./Navigation";
import Footer from "./Footer";

function Layout(props: {children: object}) {
    return (
        <div>
            <Navigation/>
                {props.children}
            <Footer/>
        </div>
    )
}

export default Layout;