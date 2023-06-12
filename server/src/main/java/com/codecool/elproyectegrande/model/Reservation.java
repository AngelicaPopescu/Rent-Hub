package com.codecool.elproyectegrande.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //TODO validari hibernate
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    Client client;


    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @PrePersist
    @PreUpdate
    private void  calculatePrice() {
        price = calculatePriceBasedOnDates();
    }

    private BigDecimal calculatePriceBasedOnDates() {
        long numberOfNights = ChronoUnit.DAYS.between(checkIn, checkOut);
        return property.getPricePerNight().multiply(BigDecimal.valueOf(numberOfNights));
    }

}
