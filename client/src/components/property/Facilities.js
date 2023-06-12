import React from "react";
import "./Facilities.css"


const Facilities = ({ facilities }) => {

    return (
        <div className="facilities">
            <ol className="property_facilities">
                <b>Facilities:</b>
                {facilities.map((facility, index) => (
                    <li key={facility.id}> &nbsp; &nbsp; {facility.name}</li>
                ))}
            </ol>
        </div>
    )
}

export default Facilities;
