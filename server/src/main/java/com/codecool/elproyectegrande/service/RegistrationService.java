package com.codecool.elproyectegrande.service;


import com.codecool.elproyectegrande.model.Client;
import com.codecool.elproyectegrande.model.ClientRole;
import com.codecool.elproyectegrande.registration.LogInRequest;
import com.codecool.elproyectegrande.registration.SignUpRequest;
import com.codecool.elproyectegrande.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final ClientService clientService;
    private final EmailValidator emailValidator;

    private final ClientRepository clientRepository;

    private final RepositoryBackedUserDetailsService repositoryBackedUserDetailsService;

    public /*String*/ void register(SignUpRequest request) {
        boolean isValidEmail = emailValidator.test(request.emailAddress());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        /*return*/ clientService.signUpClient(new Client(
                request.name()
                , request.surname()
                , request.phoneNumber()
                , request.emailAddress()
                , request.password()
                , ClientRole.USER));
    }

    public String logIn(LogInRequest request) {

        System.out.println("req email:"+request.emailAddress()+"  req pass: "+request.password() );
        repositoryBackedUserDetailsService.loadUserByUsername(request.emailAddress());
//        Client client = clientRepository.findByEmailAddress(request.emailAddress()).orElse(null);
        return clientService.logInUser(request.emailAddress(), request.password());
    }
}
