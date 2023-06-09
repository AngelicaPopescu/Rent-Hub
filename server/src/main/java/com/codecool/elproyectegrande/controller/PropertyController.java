package com.codecool.elproyectegrande.controller;

import com.codecool.elproyectegrande.DTO.ReservationRequest;
import com.codecool.elproyectegrande.exception.ErrorResponse;
import com.codecool.elproyectegrande.exception.ReservationConflictException;
import com.codecool.elproyectegrande.model.Category;
import com.codecool.elproyectegrande.model.Property;
import com.codecool.elproyectegrande.model.Reservation;
import com.codecool.elproyectegrande.model.Review;
import com.codecool.elproyectegrande.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/properties")
//@CrossOrigin(origins = "http://localhost:3000"
//        , methods = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE
//        , RequestMethod.POST, RequestMethod.PATCH}, allowedHeaders = "*", allowCredentials = "true")
public class PropertyController {
    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{id}")
    public Property getPropertyDetails(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    @PostMapping
    public void addProperty(@RequestBody Property property) {
        propertyService.addProperty(property);
    }

    @PostMapping("/{propertyId}/reviews")
    public void addPropertyReview(@PathVariable Long propertyId, @RequestBody Review review) {
        propertyService.addReviewForProperty(propertyId, review);
    }

    @GetMapping(params = "category")
    public List<Property> getPropertiesByCategory(@RequestParam("category") String category) {
        return propertyService.getPropertiesByCategory(category);
    }

    @PatchMapping("/{propertyId}/category")
    public void addCategory(@PathVariable Long propertyId, @RequestBody Category category) {
        propertyService.addCategory(propertyId, category);
    }

    @PostMapping(value = "/{propertyId}/reservations", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addReservation(@PathVariable Long propertyId, @RequestBody ReservationRequest reservationRequest) {
        System.out.println(reservationRequest);
        try {
            Reservation savedReservation = propertyService.addReservation(propertyId, reservationRequest);
            return ResponseEntity.ok(savedReservation);
        } catch (ReservationConflictException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
