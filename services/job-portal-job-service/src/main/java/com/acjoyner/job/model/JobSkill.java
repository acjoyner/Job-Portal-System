package com.acjoyner.job.model;


import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.acjoyner.job.domain.SkillCategory;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "job_skills")
public class JobSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Column(nullable = false, unique = true)
    private String name;
     
    @Column( unique = true)
    private String slug;

    private SkillCategory category;

    private Boolean active = true;

    @Column(nullable = false, unique = true)
    @UpdateTimestamp
    private LocalDateTime createdatedAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
