import React from "react";
import "./PropertyTopDetails.css"


const PropertyTopDetails = ({ props }) => {
    return (
        <div className="property__description">
            <h3 className="property__name">{props.name}</h3>
            <div className="property__rating">
                <div className="component font-bold">
                    <button id={"property__rating-button"}> &#9733; {props.rating} </button>
                    &nbsp; &nbsp;
                    {props.reviews && props.reviews.length > 0 && (
                        <p>{props.reviews.length} Reviews</p>
                    )}
                    {props.location.city},&nbsp;{props.location.country}
                </div>
            </div>
      </div>
    )
};

export default PropertyTopDetails;
