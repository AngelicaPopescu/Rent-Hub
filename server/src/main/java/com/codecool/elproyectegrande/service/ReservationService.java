package com.codecool.elproyectegrande.service;

import com.codecool.elproyectegrande.exception.ReservationConflictException;
import com.codecool.elproyectegrande.model.Reservation;
import com.codecool.elproyectegrande.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) throws ReservationConflictException {
        validateReservation(reservation);
        saveReservation(reservation);
        return reservation;
    }

    private void validateReservation(Reservation reservation) throws ReservationConflictException {
        List<Reservation> overlappingReservations = reservationRepository
                .findByPropertyIdAndCheckOutAfterAndCheckInBefore(
                        reservation.getProperty().getId(),
                        reservation.getCheckIn(),
                        reservation.getCheckOut());

        for (Reservation overlappingReservation : overlappingReservations) {
            LocalDate overlappingCheckIn = overlappingReservation.getCheckIn();
            LocalDate overlappingCheckOut = overlappingReservation.getCheckOut();

            LocalDate checkIn = reservation.getCheckIn();
            LocalDate checkOut = reservation.getCheckOut();

            if (checkIn.isEqual(overlappingCheckIn)
                    || checkOut.isEqual(overlappingCheckOut)
                    || (checkIn.isBefore(overlappingCheckOut) && checkOut.isAfter(overlappingCheckIn))) {
                throw new ReservationConflictException("The selected period is unavailable");
            }
        }
    }

    private void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationForClient(Long id) {
        return reservationRepository.findByClientId(id);
    }

}
