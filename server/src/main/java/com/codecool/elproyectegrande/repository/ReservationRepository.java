package com.codecool.elproyectegrande.repository;

import com.codecool.elproyectegrande.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByPropertyIdAndCheckOutAfterAndCheckInBefore(
            Long propertyId, LocalDate checkIn, LocalDate checkOut);

    List<Reservation> findByClientId(Long clientId);
}
