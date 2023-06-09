package com.codecool.elproyectegrande;

import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNullApi;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

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

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry.addMapping("/greeting-javaconfig")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
		ConfigurableApplicationContext run = SpringApplication.run(ElProyecteGrandeApplication.class, args);
		Arrays.asList(run.getBeanDefinitionNames()).forEach(System.out::println);
	}

	@PostConstruct
	public void seedDatabase() {
		initService.seedDatabase();
	}




}
