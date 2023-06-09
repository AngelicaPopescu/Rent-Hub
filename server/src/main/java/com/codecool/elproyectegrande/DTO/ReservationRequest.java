package com.codecool.elproyectegrande.DTO;

import com.codecool.elproyectegrande.model.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationRequest {

    private Long clientId;
    private Reservation reservation;

    public ReservationRequest(@JsonProperty("clientId") Long clientId, @JsonProperty("reservation") Reservation reservation) {
        this.clientId = clientId;
        this.reservation = reservation;
    }
    public Long getClientId() {
        return clientId;
    }

    public Reservation getReservation() {
        return reservation;
    }

}
