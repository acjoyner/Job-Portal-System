package com.acjoyner.job.model.embeddable;

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
public class JobLocation {
    private String address;
    private String city;
    private String county;
    private String state;
    private String zipCode;

}
