package com.codecool.elproyectegrande.controller;

import com.codecool.elproyectegrande.DTO.ReviewRequest;
import com.codecool.elproyectegrande.model.Client;
import com.codecool.elproyectegrande.model.Reservation;
import com.codecool.elproyectegrande.model.Review;
import com.codecool.elproyectegrande.service.ClientService;
import com.codecool.elproyectegrande.service.ReservationService;
import com.codecool.elproyectegrande.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private ClientService clientService;
    private ReservationService reservationService;
    private ReviewService reviewService;


    @Autowired
    public ClientController(ClientService clientService, ReservationService reservationService, ReviewService reviewService) {
        this.clientService = clientService;
        this.reservationService = reservationService;
        this.reviewService = reviewService;
    }

    @GetMapping
//    @Secured({"ROLE_USER"})
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PatchMapping
    public void addClient(@RequestBody Client client) {
        clientService.addClient(client);
    }

    @GetMapping("/{id}")
//    @Secured({"ROLE_USER"})
    public Client getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }
    @PatchMapping("/{name}/{surname}")
    public Client getClientByName(@PathVariable String name, @PathVariable String surname) {
        return clientService.getClientByName(name, surname);
    }

    @PatchMapping("/{phoneNo}")
    public Client getClientByPhone(@PathVariable String phoneNo) {
        return clientService.getClientByPhone(phoneNo);
    }

    @PutMapping("/{id}")
    public Client updateClientData(@PathVariable Long id, @RequestBody Client client){
        return clientService.updateClientData(id, client);
    }

    @GetMapping("/{id}/reservations")
    public List<Reservation> getReservationForClient(@PathVariable Long id) {
        return reservationService.getReservationForClient(id);
    }

    @PostMapping(value = "/{clientId}/reviews", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPropertyReview(@PathVariable int clientId, @RequestBody ReviewRequest reviewRequest) {
         reviewService.addReview(clientId, reviewRequest);
    }

}
