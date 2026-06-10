package com.acjoyner.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.acjoyner.job.companyservice", "com.acjoyner.job.dto"})
public class JobPortalCompanyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalCompanyServiceApplication.class, args);
	}

}
