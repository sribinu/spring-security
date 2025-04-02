package com.example.spring_security.service;

import com.example.spring_security.model.Admin;
import com.example.spring_security.model.Users;
import com.example.spring_security.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public void addUser(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
    }

    public Boolean loginCheck(Admin admin) {
        Admin admin1 = adminRepository.findByUsername(admin.getUsername());
        if (admin1 != null && passwordEncoder.matches(admin.getPassword(), admin1.getPassword()))
            return true;
        else
            return false;
    }
}
