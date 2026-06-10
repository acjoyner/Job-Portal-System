package com.acjoyner.job.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.acjoyner.job.domain.CompanySize;
import com.acjoyner.job.domain.CompanyStatus;
import com.acjoyner.job.domain.CompanyType;
import com.acjoyner.job.domain.IndustryType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponse {
    
    private long id;
    private String name;
    private String slug;
    private String tagline;
    private String description;
    private String logoUrl;
    private String coverImageUrl;
    private String website;
    private Integer foundedYear;

    private String email;
    private String phone;

    private CompanySize companySize;
    private CompanyType companyType;
    private IndustryType industryType;
    private CompanyStatus status;
    private LocalDateTime verifiedAt;
    private String registrationNumber;
    private Long ownerId;
    private List<SocialLinkResponse> socialLinks;
    @Builder.Default
    private Boolean active = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
