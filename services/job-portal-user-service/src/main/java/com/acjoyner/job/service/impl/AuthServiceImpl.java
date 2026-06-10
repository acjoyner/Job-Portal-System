package com.acjoyner.job.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.acjoyner.job.domain.UserRole;
import com.acjoyner.job.domain.UserStatus;
import com.acjoyner.job.mapper.UserMapper;
import com.acjoyner.job.model.User;
import com.acjoyner.job.payload.AuthResponse;
import com.acjoyner.job.payload.LoginRequest;
import com.acjoyner.job.payload.SignupRequest;
import com.acjoyner.job.repository.UserRepository;
import com.acjoyner.job.security.JwtProvider;
import com.acjoyner.job.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public AuthResponse signup(SignupRequest req) {
        log.info("Signup request for email: {}", req.getEmail());
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            log.warn("Signup failed: Email already in use: {}", req.getEmail());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use: " + req.getEmail());
        }

        if (req.getRole() == UserRole.ROLE_ADMIN) {
            log.warn("Signup failed: Cannot assign admin role for email: {}", req.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot assign admin role");
        }

        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(req.getRole())
                .phone(req.getPhone())
                .lastLogin(LocalDateTime.now())
                .status(UserStatus.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);
        log.info("User registered successfully: {}", savedUser.getEmail());

        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(savedUser.getRole().toString()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication, savedUser.getId());
        
        AuthResponse res = new AuthResponse();
        res.setTitle("welcome " + savedUser.getFullName());
        res.setMessage("Registered successfully");
        res.setJwt(jwt);
        res.setUser(userMapper.toDTO(savedUser));
        return res;
    }

    @Override
    public AuthResponse login(LoginRequest req) {
        log.info("Login attempt for email: {}", req.getEmail());
        User user = userRepository.findByEmail(req.getEmail()).orElseGet(() -> {
            log.warn("Login failed: User not found with email: {}", req.getEmail());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        });

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            log.warn("Login failed: Password mismatch for email: {}", req.getEmail());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        log.info("Login successful for email: {}", req.getEmail());
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorities);

        String jwt = jwtProvider.generateToken(authentication, user.getId());

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse res = new AuthResponse();
        res.setTitle("welcome back " + user.getFullName());
        res.setMessage("Login successful");
        res.setJwt(jwt);
        res.setUser(userMapper.toDTO(user));
        return res;
    }
}
