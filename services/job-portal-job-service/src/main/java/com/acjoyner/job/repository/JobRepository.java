package com.acjoyner.job.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.acjoyner.job.model.Job;


public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job>{

    List<Job> findByCompanyId(Long companyId);

}
