package com.acjoyner.job.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.acjoyner.job.domain.UserRole;
import com.acjoyner.job.domain.UserStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String profileImage;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserRole role= UserRole.ROLE_JOB_SEEKER;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @CreationTimestamp
    @Column(nullable= false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable= false)
    private LocalDateTime updatedAt;

    private LocalDateTime lastLogin;

    private LocalDateTime suspendedAt;

    private LocalDateTime deletedAt;

}
