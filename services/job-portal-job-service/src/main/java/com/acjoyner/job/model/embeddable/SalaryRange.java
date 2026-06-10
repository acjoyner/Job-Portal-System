package com.acjoyner.job.model.embeddable;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryRange {
    private BigDecimal minSalary;
    private BigDecimal maxSalary;


    
}
