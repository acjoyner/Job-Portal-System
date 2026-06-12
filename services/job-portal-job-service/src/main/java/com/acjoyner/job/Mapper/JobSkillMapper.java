package com.acjoyner.job.mapper;

import com.acjoyner.job.dto.JobSkillResponse;
import com.acjoyner.job.model.JobSkill;

public class JobSkillMapper {

    public static JobSkillResponse toJobSkillResponse(JobSkill jobSkill) {
        if (jobSkill == null) {
            return null;
        }

        return JobSkillResponse.builder()
                .id(jobSkill.getId())
                .name(jobSkill.getName())
                .category(jobSkill.getCategory())
                .build();
    }

}
