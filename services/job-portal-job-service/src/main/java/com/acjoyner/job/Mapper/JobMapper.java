package com.acjoyner.job.mapper;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import com.acjoyner.job.dto.CompanyResponse;
import com.acjoyner.job.dto.JobResponse;
import com.acjoyner.job.dto.JobSkillResponse;
import com.acjoyner.job.dto.JobTagResponse;
import com.acjoyner.job.model.Job;
import com.acjoyner.job.model.embeddable.JobLocation;
import com.acjoyner.job.model.embeddable.SalaryRange;

public class JobMapper {

    public static JobResponse toResponse(Job job, CompanyResponse companyResponse){
        if (job == null) {
            return null;
        }
        
        JobLocation loc = job.getLocation();
        SalaryRange sal = job.getSalaryRange();

        Set<JobSkillResponse> skills = job.getSkills() == null?
            Collections.emptySet()
            :job.getSkills().stream().map(JobSkillMapper::toJobSkillResponse)
            .collect(Collectors.toSet());

        Set<JobTagResponse> tags = job.getTags()==null?
        Collections.emptySet():
        job.getTags().stream().map(JobTagMapper::toTagResponse)
        .collect(Collectors.toSet());

        return JobResponse.builder()
        .id(job.getId())
        .title(job.getTitle())
        .description(job.getDescription())
        .requirements(job.getRequirements())
        .responsibilities(job.getResponsibilities())
        .benefits(job.getBenefits())
        .company(companyResponse)
        .category(JobCategoryMapper.toJobCategoryResponse(job.getCategory(), false))
        .skills(skills)
        .tags(tags)
        .address(loc != null ? loc.getAddress() : null)
        .city(loc != null ? loc.getCity() : null)
        .state(loc != null ? loc.getState() : null)
        .country(loc != null ? loc.getCounty() : null) // Note: JobLocation has 'county' instead of 'country'
        .zipCode(loc != null ? loc.getZipCode() : null)
        .minSalary(sal != null ? sal.getMinSalary() : null)
        .maxSalary(sal != null ? sal.getMaxSalary() : null)
        
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
