import React from "react";
import { Link } from "react-router-dom";
import "../../style/footer.css"

function Footer() {
    return (
        <div>
            <div className="footer">
                <div className="footer-block">
                    <div className="footer-block-links">
                        <div className="footer-block-links-inner">
                            <div className="footer-social">
                                <a id="git-hub-link" href="https://github.com/Gurmigou/infopulse-project">
                                    <i className="fab fa-github fa-2x"/>
                                </a>
                            </div>
                            <div className="footer-nav">
                                <Link id="real-link" className="footer-nav-link" to="/">
                                    <a>Home</a>
                                </Link>
                                <div className="footer-nav-link">
                                    <a>|</a>
                                </div>
                                <Link id="real-link" className="footer-nav-link" to="/user/account">
                                    <a>Account</a>
                                </Link>
                                <div className="footer-nav-link">
                                    <a>|</a>
                                </div>
                                <Link id="real-link" className="footer-nav-link" to="/about">
                                    <a>About</a>
                                </Link>
                            </div>
                        </div>
                    </div>
                    <div id="separate-line-block">
                        <hr id="separate-line"/>
                    </div>
                    <div className="footer-block-about">
                        <div className="footer-block-about-inner">
                            <div className="footer-about">
                                <a>Educational project. Create your forms easily using Edulse.</a>
                            </div>
                            <div className="footer-copyright">
                                &copy;{new Date().getFullYear()} EDULSE FORMS
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Footer;