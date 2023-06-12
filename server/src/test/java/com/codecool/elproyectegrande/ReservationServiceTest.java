package com.codecool.elproyectegrande;

import com.codecool.elproyectegrande.exception.ReservationConflictException;
import com.codecool.elproyectegrande.model.Property;
import com.codecool.elproyectegrande.model.RentalUnit;
import com.codecool.elproyectegrande.model.Reservation;
import com.codecool.elproyectegrande.repository.ReservationRepository;
import com.codecool.elproyectegrande.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;
    @InjectMocks
    private ReservationService reservationService;
    private Property property;
    private RentalUnit rentalUnit;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        property = new Property();
        rentalUnit = new RentalUnit();
    }

    @Test
    public void testCreateReservation() throws ReservationConflictException {
        // Set up a test reservation that does not overlap with any existing reservations
        LocalDate checkIn = LocalDate.of(2023, 5, 10);
        LocalDate checkOut = LocalDate.of(2023, 5, 12);
        Reservation reservation1 = Reservation.builder()
                .property(property)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .build();

        // Configure the mock repository to return an empty list of overlapping reservations
        when(reservationRepository.findByPropertyIdAndCheckOutAfterAndCheckInBefore(
                property.getId(), checkIn, checkOut))
                .thenReturn(Collections.emptyList());

        // This should not throw an exception because there are no overlapping reservations
        assertDoesNotThrow(() -> {
            reservationService.createReservation(reservation1);
        });

        // Set up a test reservation that overlaps with an existing
        //                .property(property)g reservation
        Reservation reservation2 = Reservation.builder()
                .checkIn(checkIn.minusDays(1))
                .checkOut(checkOut.plusDays(1))
                .build();

        // Configure the mock repository to return a list containing the overlapping reservation
        when(reservationRepository.findByPropertyIdAndCheckOutAfterAndCheckInBefore(
                property.getId(), checkIn.minusDays(1), checkOut.plusDays(1)))
                .thenReturn(Arrays.asList(reservation1));

        // This should throw a ReservationConflictException because of the overlapping reservation
        assertThrows(ReservationConflictException.class, () -> {
            reservationService.createReservation(reservation2);
        });
    }
}
