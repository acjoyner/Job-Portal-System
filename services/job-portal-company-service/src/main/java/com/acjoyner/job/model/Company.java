package com.acjoyner.job.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.acjoyner.job.domain.CompanySize;
import com.acjoyner.job.domain.CompanyStatus;
import com.acjoyner.job.domain.CompanyType;
import com.acjoyner.job.domain.IndustryType;

import jakarta.persistence.Column;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true)
    private String slug;

    private String tagline;

    private String description;

    private String logoUrl;

    private String coverImageUrl;

    private String website;
    private Integer foundedYear;

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private CompanySize companySize;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @Enumerated(EnumType.STRING)
    private IndustryType industryType;

    @Enumerated(EnumType.ORDINAL)
    private CompanyStatus status;

    @Column(name = "is_verified")
    @Builder.Default
    private boolean verified = false;

    private LocalDateTime verifiedAt;

    @Column(unique = true)
    private String registrationNumber;

    @Column(nullable = false, unique = true)
    private Long ownerId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "company_social_links", joinColumns = @JoinColumn(name = "company_id"))
    private List<SocialLink> socialLinks;

    @Builder.Default
    private Boolean active = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

