package com.codecool.elproyectegrande.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoder {

    public static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    private PasswordEncoder() {
    }

    public static String passwordEncoder(String password){
        return passwordEncoder.encode(password);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
