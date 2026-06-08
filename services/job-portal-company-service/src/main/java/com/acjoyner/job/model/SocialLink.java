package com.acjoyner.job.model;

import com.acjoyner.job.domain.SocialPlatform;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLink {

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private SocialPlatform platform;
    private String url;
    

}
