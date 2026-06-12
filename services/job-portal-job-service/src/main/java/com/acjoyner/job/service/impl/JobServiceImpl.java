package com.acjoyner.job.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.acjoyner.job.domain.JobStatus;
import com.acjoyner.job.dto.CompanyResponse;
import com.acjoyner.job.dto.JobRequest;
import com.acjoyner.job.dto.JobResponse;
import com.acjoyner.job.mapper.JobMapper;
import com.acjoyner.job.model.Job;
import com.acjoyner.job.model.JobCategory;
import com.acjoyner.job.model.JobSkill;
import com.acjoyner.job.model.JobTag;
import com.acjoyner.job.model.embeddable.JobLocation;
import com.acjoyner.job.model.embeddable.SalaryRange;
import com.acjoyner.job.payload.JobSearchRequest;
import com.acjoyner.job.repository.JobRepository;
import com.acjoyner.job.repository.JobSpecification;
import com.acjoyner.job.service.JobCategoryService;
import com.acjoyner.job.service.JobService;
import com.acjoyner.job.service.JobSkillService;
import com.acjoyner.job.service.JobTagService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobCategoryService categoryService;
    private final JobSkillService skillService;
    private final JobTagService tagService;

    @Override
    public JobResponse createJob(Long employerId, JobRequest req) throws Exception {

        JobCategory category = categoryService.getCategoryEntityById(req.getCategoryId());

        Set<JobSkill> skills = req.getSkillIds() != null ? skillService.getSkillsByIds(req.getSkillIds())
                : Collections.emptySet();

        Set<JobTag> tags = req.getTagIds() != null ? tagService.getTagsByIds(req.getTagIds())
                : Collections.emptySet();

        Long companyId = 1L;
        Job job = Job.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .requirements(req.getRequirements())
                .responsibilities(req.getResponsibilities())
                .benefits(req.getBenefits())
                .companyId(req.getCompanyId())
                .employeeId(employerId)
                .category(category)
                .skills(skills)
                .tags(tags)
                .location(buildLocation(req))
                .salaryRange(buildSalaryRang(req))
                .jobType(req.getJobType())
                .workMode(req.getWorkMode())
                .experienceLevel(req.getExperienceLevel())
                .openings(req.getOpenings() != null ? req.getOpenings() : 1)
                .applicationDeadLine(req.getApplicationDeadline())
                .expiresAt(req.getExpiresAt())
                .build();

        Job savedJob = jobRepository.save(job);

        return convertToResponse(savedJob);
    }

    private JobResponse convertToResponse(Job savedJob) {

        // todo: fetch comapany response
        CompanyResponse companyResponse = CompanyResponse.builder()
                .id(savedJob.getId())
                .build();

        return JobMapper.toResponse(savedJob, companyResponse);
    }

    private SalaryRange buildSalaryRang(JobRequest req) {
        return SalaryRange.builder()
                .minSalary(req.getMinSalary())
                .maxSalary(req.getMaxSalary())
                .build();
    }

    private JobLocation buildLocation(JobRequest req) {
        return JobLocation.builder()
                .address(req.getAddress())
                .city(req.getCity())
                .state(req.getState())
                .county(req.getCountry())
                .zipCode(req.getZipCode())
                .build();
    }

    @Override
    public JobResponse getJobById(Long id) throws Exception {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new Exception("Job not found"));
        return convertToResponse(job);
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest request) {
        List<Job> jobs = jobRepository.findAll(JobSpecification.build(request));
        return jobs.stream().map(
                this::convertToResponse).toList();
    }

    @Override
    public List<JobResponse> getJobsByCompany(Long companyId) {
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        return jobs.stream().map(
                this::convertToResponse).toList();
    }

    @Override
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest req) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new Exception("Job not found"));

        assetEmployer(job, employerId);

        JobCategory category = categoryService.getCategoryEntityById(req.getCategoryId());

        Set<JobSkill> skills = req.getSkillIds() != null ? skillService.getSkillsByIds(req.getSkillIds())
                : Collections.emptySet();

        Set<JobTag> tags = req.getTagIds() != null ? tagService.getTagsByIds(req.getTagIds())
                : Collections.emptySet();
        job.setTitle(req.getTitle());
        job.setDescription(req.getDescription());
        job.setRequirements(req.getRequirements());
        job.setResponsibilities(req.getResponsibilities());
        job.setBenefits(req.getBenefits());

        job.setCategory(category);
        job.setSkills(skills);
        job.setTags(tags);
        job.setLocation(buildLocation(req));
        job.setSalaryRange(buildSalaryRang(req));
        job.setJobType(req.getJobType());
        job.setWorkMode(req.getWorkMode());
        job.setExperienceLevel(req.getExperienceLevel());
        job.setOpenings(req.getOpenings());
        job.setApplicationDeadLine(req.getApplicationDeadline());
        job.setExpiresAt(req.getExpiresAt());
        return convertToResponse(jobRepository.save(job));
    }

    @Override
    public JobResponse publishJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new Exception("Job not found"));
        assetEmployer(job, employerId);
        if (job.getStatus() == JobStatus.CLOSED || job.getStatus() == JobStatus.EXPIRED) {
            throw new Exception("Job is expired.");
        }
        job.setStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        job.setActive(true);
        return convertToResponse(jobRepository.save(job));
    }

    @Override
    public JobResponse closeJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new Exception("Job not found"));
        assetEmployer(job, employerId);

        job.setStatus(JobStatus.CLOSED);
        job.setClosedAt(LocalDateTime.now());
        job.setActive(false);
        return convertToResponse(jobRepository.save(job));
    }

    @Override
    public void deleteJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new Exception("Job not found"));
        assetEmployer(job, employerId);
        jobRepository.delete(job);

    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {
        return jobRepository.findAll()
                .stream()
                .map(this::convertToResponse).toList();
    }

    private void assetEmployer(Job job, Long employerId) throws Exception {
        if (!job.getEmployeeId().equals(employerId)) {
            throw new Exception("You are not the employer who posted this job.");
        }
    }

}
