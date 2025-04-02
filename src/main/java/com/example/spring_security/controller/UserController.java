package com.example.spring_security.controller;

import com.example.spring_security.model.Users;
import com.example.spring_security.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/add-user")
    public void addUser(@RequestBody Users user) {
        usersService.addUser(user);
    }

    @GetMapping("/user/home")
    public String homeData() {
        return "success";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/about")
    public String aboutData() {
        return "About Page";
    }

//    @GetMapping("/adminLogin")
//    public String loginPage(Model model) {
//        model.addAttribute("users",new Users());
//        return "login";
//    }

    @GetMapping("/userLogin")
    public String userLogin(Model model) {
        model.addAttribute("users",new Users());
        return "user-login";
    }

    @PostMapping("/userLogin")
    public String loginSuccess(@ModelAttribute("users") Users user) {
        Boolean check = usersService.loginCheck(user);
        if (check)
            return "success";
        else
            return "user-login";
    }

}
