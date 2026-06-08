package com.acjoyner.job.mapper;

import com.acjoyner.job.dto.response.UserResponse;
import com.acjoyner.job.model.User;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public static UserResponse toDTO(User user) {
        if (user == null) {
            return null;
        }
        
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setPhone(user.getPhone());
        dto.setProfileImage(user.getProfileImage()); 
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setLastLogin(user.getLastLogin());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }

    public static List<UserResponse> toDTOList(List<User> users) {
        return users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }
}
