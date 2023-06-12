import React from "react";


const PropertyDescription = ({ description }) => {
    return (
        <div className="property__about">
            <div className="property__about-title">
                <h4>About this property</h4>
            </div>
            <p>{description}</p>
        </div>
    )
}

export default PropertyDescription;
