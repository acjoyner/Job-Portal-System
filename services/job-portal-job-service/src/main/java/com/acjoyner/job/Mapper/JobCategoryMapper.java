package com.acjoyner.job.mapper;

import java.util.List;

import com.acjoyner.job.dto.JobCategoryResponse;
import com.acjoyner.job.model.JobCategory;

public class JobCategoryMapper {

    public static JobCategoryResponse toJobCategoryResponse(JobCategory category, boolean includeChildren) {
        if (category == null) {
            return null;
        }

        List<JobCategoryResponse> subCategories = null;

        if (includeChildren && category.getSubcategories() != null) {
            subCategories = category.getSubcategories()
                    .stream()
                    .map(sub -> toJobCategoryResponse(sub, false))
                    .toList();
        }

        return JobCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .description(category.getDescription())
                .iconUrl(category.getIconUrl())
                .active(category.getActive())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .parentName(category.getParent() != null ? category.getParent().getName() : null)
                .subCategories(subCategories)
                .createdAt(category.getCreatedAt())
                .build();
    }

}
