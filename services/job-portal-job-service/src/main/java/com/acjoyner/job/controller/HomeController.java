package com.acjoyner.job.controller;

import com.acjoyner.job.domain.UserRole;
import com.acjoyner.job.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ApiResponse home() {
        return new ApiResponse("Service for managing job postings, search, and filtering", UserRole.ROLE_EMPLOYER, true);
    }

}
