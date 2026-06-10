package com.acjoyner.job.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.acjoyner.job.dto.response.UserResponse;
import com.acjoyner.job.mapper.UserMapper;
import com.acjoyner.job.model.User;
import com.acjoyner.job.payload.UpdateUserRequest;
import com.acjoyner.job.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users/profile")
    public ResponseEntity<UserResponse> getProfile(
            @RequestHeader("X-User-Email") String email) throws Exception {

        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @PutMapping("/api/users/profile")
    public ResponseEntity<UserResponse> updateProfile(
            @RequestHeader("X-User-Email") String email,
            @RequestBody UpdateUserRequest req) throws Exception {

        return ResponseEntity.ok(userService.updateProfile(email, req));
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long userId) throws Exception {

        User user = userService.getUserById(userId);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() throws Exception {
        return ResponseEntity.ok(UserMapper.toDTOList(userService.getAllUsers()));
    }

    @PatchMapping("/api/users/{userId}/suspend")
    public ResponseEntity<UserResponse> suspendUser(
            @PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.suspendUser(userId));
    }

    @PatchMapping("/api/users/{userId}/activate")
    public ResponseEntity<UserResponse> activateUser(
            @PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.activateUser(userId));
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<UserResponse> deleteUser(
            @PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
