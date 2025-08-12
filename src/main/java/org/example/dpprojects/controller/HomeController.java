package org.example.dpprojects.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Application is running! Access H2 console at: http://localhost:8080/h2-console";
    }

    @GetMapping("/test")
    public String test() {
        return "Test endpoint works!";
    }
}