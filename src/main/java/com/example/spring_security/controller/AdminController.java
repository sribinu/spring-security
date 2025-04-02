package com.example.spring_security.controller;

import com.example.spring_security.model.Admin;
import com.example.spring_security.model.Users;
import com.example.spring_security.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/add-admin")
    public void addUser(@RequestBody Admin admin) {
        adminService.addUser(admin);
    }

    @GetMapping("/adminLogin")
    public String loginPage(Model model) {
        model.addAttribute("admin",new Admin());
        return "login";
    }

    @PostMapping("/adminLogin")
    public String loginSuccess(@ModelAttribute("admin") Admin admin) {
        Boolean check = adminService.loginCheck(admin);
        if (check)
            return "success";
        else
            return "login";
    }

}
