package com.codecool.elproyectegrande.service;

import com.codecool.elproyectegrande.model.Client;
import com.codecool.elproyectegrande.model.ClientRole;
import com.codecool.elproyectegrande.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    private final ClientRepository clientRepository;

    @Autowired
    public InitService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public void seedDatabase (){

        Client client1 = Client.builder()
                .name("m")
                .surname("m")
                .emailAddress("m@m.com")
                .password(PasswordEncoder.passwordEncoder("123"))
                .phoneNumber("0711.111.111")
                .clientRole(ClientRole.valueOf("USER"))
                .build();
        clientRepository.saveAndFlush(client1);

        Client client2 = Client.builder()
                .name("x")
                .surname("x")
                .emailAddress("x@x.com")
                .password(PasswordEncoder.passwordEncoder("123"))
                .phoneNumber("0722.222.222")
                .clientRole(ClientRole.valueOf("USER"))
                .build();
        clientRepository.saveAndFlush(client2);

        Client client3 = Client.builder()
                .name("Marin")
                .surname("Ion")
                .emailAddress("marin@ion.com")
                .password(PasswordEncoder.passwordEncoder("123"))
                .phoneNumber("0733.333.333")
                .clientRole(ClientRole.valueOf("USER"))
                .build();
        clientRepository.saveAndFlush(client3);

        Client client4 = Client.builder()
                .name("Nae")
                .surname("Creanga")
                .emailAddress("nae@creanga.com")
                .password(PasswordEncoder.passwordEncoder("123"))
                .phoneNumber("0744.444.444")
                .clientRole(ClientRole.valueOf("ADMIN"))
                .build();
        clientRepository.saveAndFlush(client4);

        Client client5 = Client.builder()
                .name("Oana")
                .surname("Matei")
                .emailAddress("oana@matei.com")
                .password(PasswordEncoder.passwordEncoder("123"))
                .phoneNumber("0755.555.555")
                .clientRole(ClientRole.valueOf("DEVELOPER"))
                .build();
        clientRepository.saveAndFlush(client5);

    }

}
