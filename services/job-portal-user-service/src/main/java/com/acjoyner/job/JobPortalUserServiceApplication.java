package com.acjoyner.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan("com.acjoyner.job.model")
public class JobPortalUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalUserServiceApplication.class, args);
	}

}

