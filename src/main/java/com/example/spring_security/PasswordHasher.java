package com.example.spring_security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        String password = "123";
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword + " : ");
    }
}
