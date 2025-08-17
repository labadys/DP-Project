package com.example.library.service;

public interface EmailService {
    void sendRegistrationEmail(String email);
    void sendPasswordResetEmail(String email);
}