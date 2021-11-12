import React from "react";
import {Link} from "react-router-dom";
import Layout from "../navbarFooter/Layout";
import "../../style/notFound.css"

function NotFound() {
    return (
        <Layout>
            <div className="all-outer-container not-found-block">
                <div className="not-found-block-inner">
                    <div className="not-found-image">
                        <div className="not-found-image-inner"/>
                    </div>
                    <div className="not-found-text">
                        <Link id="link-to-main" to="/">
                            <a id="text">Ups-s! 404 Page not found!</a>
                        </Link>
                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default NotFound;

