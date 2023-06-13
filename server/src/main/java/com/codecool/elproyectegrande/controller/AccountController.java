package com.codecool.elproyectegrande.controller;


import com.codecool.elproyectegrande.model.Client;
import com.codecool.elproyectegrande.registration.LogInRequest;
import com.codecool.elproyectegrande.registration.SignUpRequest;
import com.codecool.elproyectegrande.repository.ClientRepository;
import com.codecool.elproyectegrande.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/accounts")
public class AccountController {

    RegistrationService registrationService;
    ClientRepository clientRepository;

    @PostMapping("/register")
        public void signUp(@RequestBody SignUpRequest request){
        /*return*/ registrationService.register(request);
    }

    @PostMapping("/login")
    public List<Serializable> logIn(@RequestBody LogInRequest request) {
        Client client = clientRepository.findByEmailAddress(request.emailAddress()).orElse(null);
        if (client!=null) {
            return List.of(registrationService.logIn(request), client.getId());
        } else return null;
    }
}
