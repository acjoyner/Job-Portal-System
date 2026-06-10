package com.acjoyner.job.payload;

import java.math.BigDecimal;
import java.util.List;

import com.acjoyner.job.domain.ExperienceLevel;
import com.acjoyner.job.domain.JobStatus;
import com.acjoyner.job.domain.JobType;
import com.acjoyner.job.domain.WorkMode;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class JobSearchRequest {

    private String keyword;

    private Long categoryId;

    private List<Long> skillIds;

    private List<Long> tagIds;

    private Long companyId;

    private String location;

    private BigDecimal minSalary;

    private BigDecimal maxSalary;

    private JobType jobType;

    private WorkMode workMode;

    private ExperienceLevel experienceLevel;

    private JobStatus status;

    private Integer minOpenings;

    private Integer maxOpenings;

}
