package com.acjoyner.job.service;

import java.util.List;
import java.util.Set;

import com.acjoyner.job.dto.JobSkillResponse;
import com.acjoyner.job.model.JobSkill;
import com.acjoyner.job.payload.JobSkillRequest;

public interface JobSkillService {

    JobSkillResponse createSkill(JobSkillRequest req) throws Exception;

    List<JobSkillResponse> getAllSkills();

    JobSkillResponse getSkillById(Long id) throws Exception;

    JobSkillResponse updateSkill(Long id, JobSkillRequest req) throws Exception;

    void deleteSkill(Long id) throws Exception;

    Set<JobSkill> getSkillsByIds(Set<Long> ids);
    
} 