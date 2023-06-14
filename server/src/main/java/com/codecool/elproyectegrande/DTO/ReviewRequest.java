package com.codecool.elproyectegrande.DTO;

import com.codecool.elproyectegrande.model.Review;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewRequest {

    private Long propertyId;
    private Review review;

    public ReviewRequest(@JsonProperty("propertyId") Long propertyId, @JsonProperty("review") Review review) {
        this.propertyId = propertyId;
        this.review = review;
    }
    public Long getPropertyId() {
        return propertyId;
    }

    public Review getReview() {
        return review;
    }
}
