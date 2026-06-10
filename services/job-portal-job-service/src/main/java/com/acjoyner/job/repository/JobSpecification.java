package com.acjoyner.job.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.acjoyner.job.domain.JobStatus;
import com.acjoyner.job.model.Job;
import com.acjoyner.job.payload.JobSearchRequest;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Path;

public class JobSpecification {

    private JobSpecification(){}

    public static Specification<Job> build(JobSearchRequest req){
        return (root, query, cb)->{
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isTrue(root.get("active")));

            JobStatus status = req.getStatus() != null?req.getStatus() : JobStatus.OPEN;
            predicates.add(cb.equal(root.get("status"), status));

            if(req.getJobType() != null){
                predicates.add(cb.equal(root.get("jobType"), req.getJobType()));
            }
            if(req.getWorkMode() != null){
                predicates.add(cb.equal(root.get("workMode"), req.getWorkMode()));
            }
            if(req.getExperienceLevel() != null){
                predicates.add(cb.equal(root.get("experienceLevel"), req.getExperienceLevel()));
            }
            if(req.getCompanyId() != null){
                predicates.add(cb.equal(root.get("companyId"), req.getCompanyId()));
            }
            if(req.getCategoryId() != null){
                predicates.add(cb.equal(root.get("category"), req.getCategoryId()));
            }
            if(req.getLocation() != null && !req.getLocation().isBlank()){
                String pattern = "%" + req.getLocation().toLowerCase() + "%";
                Path<String> city = root.get("location").get("city");
                Path<String> state = root.get("location").get("state");
                Path<String> country = root.get("location").get("country");
                predicates.add(cb.or(
                    cb.like(cb.lower(city), pattern),
                    cb.like(cb.lower(state), pattern),
                    cb.like(cb.lower(country), pattern)
                ));
            }

            if(req.getMinSalary() != null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("salaryRange").get("MaxSalary"), req.getMaxOpenings()));
            }
            if(req.getMaxOpenings() != null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("salaryRange").get("MinSalary"), req.getMinSalary()));
            }

            // Openings
            if(req.getMinOpenings() != null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("openings"), req.getMinOpenings()));
            }
            if(req.getMaxOpenings() != null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("openings"), req.getMaxOpenings()));
            }

            //todo: Filter for tag, skills

            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
