package com.acjoyner.job.payload;

import com.acjoyner.job.domain.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class SignupRequest {
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Full name is required")
    private String fullName;
    @NotBlank(message = "Password is required")
    private String password;
    private String phone;
    @NotNull(message = "Role is required")
    private UserRole role;

}
