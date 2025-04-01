package com.example.spring_security.controller;

import com.example.spring_security.model.Users;
import com.example.spring_security.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/admin/add-user")
    public void addUser(@RequestBody Users user) {
        usersService.addUser(user);
    }
}
