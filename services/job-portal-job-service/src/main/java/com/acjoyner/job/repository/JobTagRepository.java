package com.acjoyner.job.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.acjoyner.job.model.JobTag;

public interface JobTagRepository extends JpaRepository<JobTag, Long>{
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
}
