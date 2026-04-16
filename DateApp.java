package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@SpringBootApplication
@RestController
public class DateApp {

    public static void main(String[] args) {
        SpringApplication.run(DateApp.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "<h1>Spring Boot Date App is Running!</h1>" +
               "<p>The current time is: " + LocalDateTime.now() + "</p>";
    }
}
