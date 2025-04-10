package com.example.spring_security.service;

import com.example.spring_security.model.Users;
import com.example.spring_security.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public void addUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    public Boolean loginCheck(Users user) {
        Users user1 = usersRepository.findByUsername(user.getUsername());
        if (user1 !=null && passwordEncoder.matches(user.getPassword(), user1.getPassword())) {
            // passwordEncoder.matches(rawPassword, encodedPassword)
            return true;
        }
        else
            return false;

    }

    public String verify(Users user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if (authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        return "fail";
    }
}
