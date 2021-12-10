import React from "react";
import {Card} from "primereact/card";

function FormTitle({title}) {
    return (
        <Card>
            <p>{title}</p>
        </Card>
    )
}

export default FormTitle