package com.acjoyner.job.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acjoyner.job.domain.UserStatus;
import com.acjoyner.job.dto.response.UserResponse;
import com.acjoyner.job.mapper.UserMapper;
import com.acjoyner.job.model.User;
import com.acjoyner.job.payload.UpdateUserRequest;
import com.acjoyner.job.repository.UserRepository;
import com.acjoyner.job.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) throws Exception {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new Exception("User not found with email: " + email));
    }

    @Override
    public User getUserById(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(
                () -> new Exception("User not found"));

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse updateProfile(String email, UpdateUserRequest req) throws Exception {
        User user = getUserByEmail(email);

        if (req.getFullName() != null) {
            user.setFullName(req.getFullName());
        }
        if (req.getPhone() != null) {
            user.setPhone(req.getPhone());
        }
        if (req.getProfileImage() != null) {
            user.setProfileImage(req.getProfileImage());
        }

        return UserMapper.toDTO(userRepository.save(user));

    }

    @Override
    public UserResponse suspendUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.SUSPENDED);
        user.setSuspendedAt(LocalDateTime.now());

        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponse activateUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.ACTIVE);
        user.setSuspendedAt(null);
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponse deleteUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.DELETED);
        user.setDeletedAt(LocalDateTime.now());
        return UserMapper.toDTO(userRepository.save(user));

    }
}
