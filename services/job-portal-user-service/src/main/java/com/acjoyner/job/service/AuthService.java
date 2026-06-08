package com.acjoyner.job.service;

import org.springframework.stereotype.Service;

import com.acjoyner.job.payload.AuthResponse;
import com.acjoyner.job.payload.LoginRequest;
import com.acjoyner.job.payload.SignupRequest;

@Service
public interface AuthService {
    AuthResponse signup(SignupRequest req);
    AuthResponse login(LoginRequest req);

}
