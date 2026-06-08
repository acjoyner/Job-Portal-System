package com.acjoyner.job.payload;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @JsonAlias({"email", "username"})
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

}
