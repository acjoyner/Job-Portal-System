package com.acjoyner.job.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acjoyner.job.dto.response.UserResponse;
import com.acjoyner.job.model.User;
import com.acjoyner.job.payload.UpdateUserRequest;

@Service
public interface UserService {

    User getUserByEmail(String email) throws Exception;
    User getUserById(Long id) throws Exception;
    List<User> getAllUsers();
    UserResponse updateProfile(String email, UpdateUserRequest req) throws Exception;

    // admin action 
    UserResponse suspendUser(Long id) throws Exception;
    UserResponse activateUser(Long id) throws Exception;
    UserResponse deleteUser(Long id) throws Exception;

}
