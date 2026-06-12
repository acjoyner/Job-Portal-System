package com.acjoyner.job.service;

import java.util.List;

import com.acjoyner.job.dto.JobCategoryResponse;
import com.acjoyner.job.model.JobCategory;
import com.acjoyner.job.service.payload.JobCategoryRequest;

public interface JobCategoryService {
    JobCategoryResponse createCategory(JobCategoryRequest req) throws Exception;

    List<JobCategoryResponse> getAllCategories();

    JobCategoryResponse updateCategory(Long id, JobCategoryResponse req) throws Exception;

    JobCategoryResponse getCategoryById(Long id) throws Exception;

    void deleteCategory(Long id) throws Exception;

    JobCategory getCategoryEntityById(Long id) throws Exception;
    
}