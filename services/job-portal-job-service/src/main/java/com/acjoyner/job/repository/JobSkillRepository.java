package com.acjoyner.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acjoyner.job.model.JobSkill;

public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {
    List<JobSkill> findByActiveTrue();
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
}
