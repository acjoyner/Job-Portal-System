package com.acjoyner.job.Mapper;

import com.acjoyner.job.dto.CompanyResponse;
import com.acjoyner.job.dto.JobResponse;
import com.acjoyner.job.model.Job;
import com.acjoyner.job.model.embeddable.JobLocation;
import com.acjoyner.job.model.embeddable.SalaryRange;

public class JobMapper {

    public static JobResponse toResponse(Job job, CompanyResponse companyResponse){
        
        JobLocation loc = job.getLocation();
        SalaryRange sal = job.getSalaryRange();


        return JobResponse.builder()
        .id(job.getId())
        .title(job.getTitle())
        .description(job.getDescription())
        .requirements(job.getRequirements())
        .responsibilities(job.getResponsibilities())
        .benefits(job.getBenefits())
        .company(companyResponse)
        // .category(job.getCategory())
        // .skills(skills)
        // .tags(tags)
        .address(loc !=null ? loc.getAddress(): null)
        .city(loc !=null ? loc.getAddress(): null)
        .state(loc !=null ? loc.getAddress(): null)
        .country(loc !=null ? loc.getAddress(): null)
        .zipCode(loc != null ? loc.getZipCode(): null)
        .minSalary(sal != null ? sal.getMinSalary(): null)
        .maxSalary(sal != null ? sal.getMinSalary(): null)
        
        .jobType(job.getJobType())
        .workMode(job.getWorkMode())
        .experienceLevel(job.getExperienceLevel())
        .status(job.getStatus())

        .openings(job.getOpenings())
        .applicationDeadline(job.getApplicationDeadLine())
        .expiresAt(job.getExpiresAt())
        .active(job.getActive())

        .createdAt(job.getCreatedAt())
        .updatedAt(job.getUpdatedAt())
        .publishedAt(job.getPublishedAt())
        .closedAt(job.getClosedAt())


        .build();
        
    }

}
