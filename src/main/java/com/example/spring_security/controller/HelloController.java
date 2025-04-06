package com.example.spring_security.controller;

import com.example.spring_security.model.Users;
import com.example.spring_security.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public")
public class HelloController {

    @Autowired
    private UsersService usersService;

    @GetMapping({"/hello","/"})
    public String hello(HttpServletRequest servlet) {
        return "Hello World! Session ID: "+servlet.getSession().getId();
    }

    @GetMapping("/home")
    public String homePage() {
        return "Home Page";
    }

    @GetMapping("/register")
    public String register() {
        return "Register Page";
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {

//      System.out.println(user);
        return usersService.verify(user);
    }



}
