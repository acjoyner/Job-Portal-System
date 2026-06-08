package com.acjoyner.job.dto;

import com.acjoyner.job.domain.SocialPlatform;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLinkResponse {
    private SocialPlatform platform;
    private String url;
}
