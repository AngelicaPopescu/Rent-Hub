package com.codecool.elproyectegrande.controller;


import com.codecool.elproyectegrande.model.Client;
import com.codecool.elproyectegrande.registration.LogInRequest;
import com.codecool.elproyectegrande.registration.SignUpRequest;
import com.codecool.elproyectegrande.repository.ClientRepository;
import com.codecool.elproyectegrande.service.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/accounts")
public class AccountController {

    RegistrationService registrationService;
    ClientRepository clientRepository;

    @PostMapping("/register")
        public void /*String*/ signUp(@RequestBody SignUpRequest request){
        /*return*/ registrationService.register(request);
    }

    @PostMapping("/login")
    public List<Serializable> /*Long*/ logIn(@RequestBody LogInRequest request) {
        Client client = clientRepository.findByEmailAddress(request.emailAddress()).orElse(null);
        if (client!=null) {
            return List.of(registrationService.logIn(request), client.getId());
        } else return null;
    }
}
