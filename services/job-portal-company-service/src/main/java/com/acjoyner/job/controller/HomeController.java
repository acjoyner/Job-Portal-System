package com.acjoyner.job.controller;

import com.acjoyner.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
    
    @GetMapping
    public String home() {
        return "Company service for managing company profiles and documents - " + UserRole.ROLE_EMPLOYER;
    }

}
