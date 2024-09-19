package com.reactivespring.medibuddyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories(basePackages = "com.reactivespring.medibuddyapp.repository")
@SpringBootApplication
public class MediBuddyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediBuddyAppApplication.class, args);
	}

}



