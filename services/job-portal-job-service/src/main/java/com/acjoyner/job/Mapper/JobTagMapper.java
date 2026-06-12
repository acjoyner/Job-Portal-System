package com.acjoyner.job.mapper;

import com.acjoyner.job.dto.JobTagResponse;
import com.acjoyner.job.model.JobTag;

public class JobTagMapper {

    public static JobTagResponse toTagResponse(JobTag tag) {
        if (tag == null) {
            return null;
        }

        return JobTagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

}
