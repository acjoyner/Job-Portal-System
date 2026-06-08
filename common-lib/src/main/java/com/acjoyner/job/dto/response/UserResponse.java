package com.acjoyner.job.dto.response;

import java.time.LocalDateTime;

import com.acjoyner.job.domain.UserRole;
import com.acjoyner.job.domain.UserStatus;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String profileImage;
    private UserRole role;
    private UserStatus status;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;

}
