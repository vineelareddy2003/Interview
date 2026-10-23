package com.project.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.interview.entity.User;
import com.project.interview.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;

    // ✅ Register API
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return service.register(user);
    }

    // ✅ Login API
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return service.login(user);
    }
}