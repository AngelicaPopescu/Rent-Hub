package com.codecool.elproyectegrande.service;

import com.codecool.elproyectegrande.DTO.ReservationRequest;
import com.codecool.elproyectegrande.DTO.ReviewRequest;
import com.codecool.elproyectegrande.exception.ReservationConflictException;
import com.codecool.elproyectegrande.model.Client;
import com.codecool.elproyectegrande.model.Property;
import com.codecool.elproyectegrande.model.Reservation;
import com.codecool.elproyectegrande.model.Review;
import com.codecool.elproyectegrande.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private ClientService clientService;
    private PropertyService propertyService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ClientService clientService, PropertyService propertyService) {
        this.reviewRepository = reviewRepository;
        this.clientService = clientService;
        this.propertyService = propertyService;
    }

    public void addReview(int clientId, ReviewRequest reviewRequest) throws ReservationConflictException {
        Client client = clientService.getClientById(clientId);
        Property property = propertyService.getPropertyById(reviewRequest.getPropertyId());
        Review newReview = Review.builder()
                .property(property)
                .description(reviewRequest.getReview().getDescription())
                .satisfaction(reviewRequest.getReview().getSatisfaction())
                .client(client)
                .build();
        createReview(newReview);
    }

    private void createReview(Review newReview) {
        reviewRepository.save(newReview);
        propertyService.updateRating(newReview.getProperty());
    }


}
