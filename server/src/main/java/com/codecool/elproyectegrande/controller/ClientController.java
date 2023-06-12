package com.codecool.elproyectegrande.controller;

import com.codecool.elproyectegrande.model.Client;
import com.codecool.elproyectegrande.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    ClientService clientService;


    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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

//    @PostMapping("/register")
//    public String registerClient(@RequestBody Client client) {
//        try {
//            clientService.registerClient(
//                    client.getName()
//                    , client.getSurname()
//                    , client.getEmailAddress()
//                    , client.getPhoneNumber()
//                    , client.getPassword());
//            return "redirect:/login-form";
//        } catch (ClientException e) {
//            System.out.println("message" + e.getMessage());
//            return "register-form";
//        }
//    }


//    @PostMapping("/login")
//    public String loginClient(@ModelAttribute("client") Client client, BindingResult result, HttpSession session, Model model) {
//        if (result.hasErrors()) {
//            return "login-form";
//        }
//
//        try {
//            Optional<Client> authenticatedClient = clientService.login(client.getEmailAddress(), client.getPassword());
//            session.setAttribute("client", authenticatedClient);
//            return "redirect:/home";
//        } catch (ClientException e) {
//            model.addAttribute("message", e.getMessage());
//            return "login-form";
//        }
//    }
//
//    @GetMapping("/home")
//    public RedirectView showHomePage(HttpSession session, Model model) {
//        Client client = (Client) session.getAttribute("client");
//        if (client == null) {
//            return new RedirectView("/api/clients/login-form");
//        }
//
//        model.addAttribute("client", client);
////        return "home";
//        return new RedirectView("/properties");
//    }


}
