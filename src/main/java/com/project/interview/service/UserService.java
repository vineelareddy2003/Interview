package com.project.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.interview.entity.User;
import com.project.interview.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // ✅ Register
    public String register(User user) {

        User existing = repo.findByUsername(user.getUsername());

        if (existing != null) {
            return "Username already exists";
        }

        repo.save(user);
        return "User registered successfully";
    }

    // ✅ Login
    public String login(User user) {

        User existing = repo.findByUsername(user.getUsername());

        if (existing == null) {
            return "User not found";
        }

        if (!existing.getPassword().equals(user.getPassword())) {
            return "Invalid password";
        }

        return "Login successful";
    }
}