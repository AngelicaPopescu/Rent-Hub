package com.codecool.elproyectegrande;

import com.codecool.elproyectegrande.service.InitService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
public class ElProyecteGrandeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElProyecteGrandeApplication.class, args);
	}

	@Autowired
	InitService initService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostConstruct
	public void seedDatabase() {
		initService.seedDatabase();
	}

}
