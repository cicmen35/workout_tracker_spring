package com.example.workouttracker;

import org.springframework.boot.SpringApplication;

public class TestWorkoutTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.from(WorkoutTrackerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
