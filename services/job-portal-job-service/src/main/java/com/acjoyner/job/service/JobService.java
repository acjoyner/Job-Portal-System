package com.acjoyner.job.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acjoyner.job.dto.JobRequest;
import com.acjoyner.job.dto.JobResponse;
import com.acjoyner.job.payload.JobSearchRequest;

@Service
public interface JobService {
    JobResponse createJob(Long employerId, JobRequest req);

    JobResponse getJobById(Long id) throws Exception;

    List<JobResponse> getJobs(JobSearchRequest request);

    List<JobResponse> getJobsByCompany(Long companyId);


    JobResponse updateJob(Long jobId, Long employerId, JobRequest req) throws Exception;

    JobResponse publishJob(Long jobId, Long employerId) throws Exception;

    JobResponse closeJob(Long jobId, Long employerId) throws Exception;

    void deleteJob(Long jobId, Long employerId) throws Exception;

    // void incrementApplicationCount(Long jobId);

    List<JobResponse> getAllJobsAdmin();

}
