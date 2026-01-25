package com.example.workouttracker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;

@SpringBootTest(classes = WorkoutTrackerApplication.class, properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration"
})
class WorkoutTrackerApplicationTests {

	@Test
	void contextLoads() {
	}

}
