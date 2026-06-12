package com.acjoyner.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acjoyner.job.model.JobCategory;



public interface JobCategoryRepository extends JpaRepository<JobCategory, Long>{

    boolean existsByName(String name);
    boolean existsBySlug(String slug);

    List<JobCategory> findByActiveTrue();

}
