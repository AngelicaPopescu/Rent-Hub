import React, {useState} from "react";
import {MDBBadge, MDBListGroup, MDBListGroupItem} from "mdb-react-ui-kit";
import "./PropertyReviews.css"

export default function PropertyReviews ({ reviews }) {

    const [expandedIndex, setExpandedIndex] = useState(-1);

    return (
        <MDBListGroup style={{ maxWidth: "25rem", paddingTop: "5vh" }} light>
        {reviews.map((review, index) => {
          const isExpanded = index === expandedIndex;
          const description = isExpanded
            ? review.description
            : `${review.description.substring(0, 100)}...`;
          return (
            <MDBListGroupItem
              key={`review-${index}`}
              className='d-flex justify-content-between align-items-center'>
              <MDBBadge pill light>
                <span>{review.satisfaction} &#9733;</span>
              </MDBBadge>
              <p>
                {description}
                {description.length < review.description.length && (
                  <button
                    onClick={() => setExpandedIndex(isExpanded ? -1 : index)}
                  >
                    {isExpanded ? "Read less" : "Read more"}
                  </button>
                )}
              </p>
            </MDBListGroupItem>
          );
        })}
      </MDBListGroup>
    )
};
