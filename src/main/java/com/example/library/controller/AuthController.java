package com.example.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication API", description = "API для аутентификации и регистрации")
public class AuthController {

    @PostMapping("/login")
    @Operation(summary = "Вход в систему", description = "Аутентификация пользователя")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/register")
    @Operation(summary = "Регистрация пользователя", description = "Создание нового аккаунта")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok("Registration successful");
    }

    public record LoginRequest(String username, String password) {}
    public record RegisterRequest(String username, String password, String email) {}
}