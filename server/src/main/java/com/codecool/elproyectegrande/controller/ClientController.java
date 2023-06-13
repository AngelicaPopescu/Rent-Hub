package com.codecool.elproyectegrande.controller;

import com.codecool.elproyectegrande.model.Client;
import com.codecool.elproyectegrande.model.Reservation;
import com.codecool.elproyectegrande.service.ClientService;
import com.codecool.elproyectegrande.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    ClientService clientService;
    ReservationService reservationService;

    @Autowired
    public ClientController(ClientService clientService, ReservationService reservationService) {
        this.clientService = clientService;
        this.reservationService = reservationService;
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


}
