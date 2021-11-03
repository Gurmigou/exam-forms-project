import React from "react";
import { Link } from "react-router-dom";

function UserFormList() {
    const someId: number = 1;

    return (
        <div>
            UserFormList
            <div>
                <h4>Passed form 1</h4>
                <Link to={"/form/" + someId + "/view-result"}>
                    <h5>View result</h5>
                </Link>
                <Link to={"/form/" + someId}>
                    <h5>Try again</h5>
                </Link>
            </div>
            <div>
                <h4>Passed form 2</h4>
                <Link to={"/form/" + someId + "/view-result"}>
                    <h5>View result</h5>
                </Link>
                <Link to={"/form/" + someId}>
                    <h5>Try again</h5>
                </Link>
            </div>
            <h3>________________________________________</h3>
            <div>
                <h4>My form 1</h4>
                <Link to={"/form/" + someId}>
                    <h5>Try to pass</h5>
                </Link>
            </div>
            <div>
                <h4>My form 2</h4>
                <Link to={"/form/" + someId}>
                    <h5>Try to pass</h5>
                </Link>
            </div>
            <div>
                <h4>My form 3</h4>
                <Link to={"/form/" + someId}>
                    <h5>Try to pass</h5>
                </Link>
            </div>
        </div>
    )
}

export default UserFormList