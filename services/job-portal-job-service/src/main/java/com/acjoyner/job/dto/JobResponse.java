package com.acjoyner.job.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.acjoyner.job.domain.ExperienceLevel;
import com.acjoyner.job.domain.JobStatus;
import com.acjoyner.job.domain.JobType;
import com.acjoyner.job.domain.WorkMode;
import com.acjoyner.job.dto.CompanySummaryResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String requirements;
    private String responsibilities;
    private String benefits;

    private CompanyResponse company;
    private Long employerId;
    private Long companyId;
    
    // private JobCategoryResponse category;
    // private Set<JobSkillResponse> skills;
    // private Set<JobTagResponse> tags;

    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String currency;
    //private SalaryPeriod salaryPeriod;
    // private Boolean salaryNegotiable;
    // private Boolean salaryDisclosed;

    private JobType jobType;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;
    private JobStatus status;

    private Integer openings;
    private LocalDate applicationDeadline;
    private LocalDate expiresAt;
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private LocalDateTime closedAt;




}
