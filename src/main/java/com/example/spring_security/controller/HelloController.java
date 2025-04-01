package com.example.spring_security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public")
public class HelloController {

    @GetMapping({"/hello","/"})
    public String hello(HttpServletRequest servlet) {
        return "Hello World! Session ID: "+servlet.getSession().getId();
    }

    @GetMapping("/home")
    public String homePage() {
        return "Home Page";
    }
}
