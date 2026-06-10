package com.acjoyner.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.acjoyner.job.userservice", "com.acjoyner.job.dto"})
public class JobPortalUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalUserServiceApplication.class, args);
	}

}
