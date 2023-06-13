package com.codecool.elproyectegrande.service;


import com.codecool.elproyectegrande.model.Client;
import com.codecool.elproyectegrande.model.ClientRole;
import com.codecool.elproyectegrande.registration.token.ConfirmationToken;
import com.codecool.elproyectegrande.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final ClientRepository clientRepository;

    private final RepositoryBackedUserDetailsService repositoryBackedUserDetailsService;


    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clientRepository
                .findByEmailAddress(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public void signUpClient(Client client){

        boolean userExists = clientRepository
                .findByEmailAddress(client.getEmailAddress())
                .isPresent();

        if (userExists){
            throw new IllegalStateException("email already taken");
        }

        client.setPassword(PasswordEncoder.passwordEncoder(client.getPassword()));

        clientRepository.save(client);

    }


    public String logInUser(String email, String password) {
        var user = clientRepository.findByEmailAddress(email);
        if (user.isEmpty() || !PasswordEncoder.matches(password, user.get().getPassword())) {
            throw new IllegalStateException("email-password combination is not a match");
        }

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                user.get()
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken
        );

        return token;
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public void addClient(Client client){
        clientRepository.save(client);
    }

    public Client getClientByName(String name, String surname){
        return clientRepository.findAll().stream()
                .filter(client -> (client.getName().equals(name)
                        && client.getSurname().equals(surname)))
                .findAny()
                .orElse(null);
    }

    public Client getClientById(int ID){
        return clientRepository.findAll().stream()
                .filter(client -> client.getId()==ID)
                .findAny()
                .orElse(null);
    }

    public Client getClientByPhone(String phoneNo){
        return clientRepository.findAll().stream()
                .filter(client -> client.getPhoneNumber().equals(phoneNo))
                .findAny()
                .orElse(null);
    }

    public Client updateClientData(Long id, Client updatedClient) {
        Client client = clientRepository.findById(id).orElse(null);
        assert client != null;
        client.setName(updatedClient.getName());
        client.setSurname(updatedClient.getSurname());
        client.setEmailAddress(updatedClient.getEmailAddress());
        client.setPhoneNumber(updatedClient.getPhoneNumber());
        clientRepository.save(client);
        return client;
    }
}
