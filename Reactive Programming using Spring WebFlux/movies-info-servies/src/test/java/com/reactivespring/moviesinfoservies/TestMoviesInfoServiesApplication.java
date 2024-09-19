package com.reactivespring.moviesinfoservies;

import org.springframework.boot.SpringApplication;

public class TestMoviesInfoServiesApplication {

	public static void main(String[] args) {
		SpringApplication.from(MoviesInfoServiesApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
