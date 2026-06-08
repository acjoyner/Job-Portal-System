package com.acjoyner.job.controller;


@RestController
import com.acjoyner.job.dto.ApiResponse;


@GetMapping
public class HomeController {
    public ApiResponse HomeController(){
        return new ApiResponse("Service for managing job postings, search, and filtering", true);
    }

}
