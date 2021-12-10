import React from "react";
import {Card} from "primereact/card";

function FormTitle({title}) {
    return (
        <Card style={{marginBottom: `30px`, boxShadow: `3px 3px 12px 2px #bebebe`, background: `#839BE0`}}>
            <p style={{fontSize: `28px`, fontWeight: `bold`}}>{title}</p>
        </Card>
    )
}

export default FormTitle