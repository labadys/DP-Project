package com.example.library.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public String login() {
        return "Login endpoint";
    }

    @PostMapping("/register")
    public String register() {
        return "Register endpoint";
    }

    public record LoginRequest(String username, String password) {}
    public record RegisterRequest(String username, String password, String email) {}
}
