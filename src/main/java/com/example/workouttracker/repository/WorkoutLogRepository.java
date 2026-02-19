package com.example.workouttracker.repository;

import com.example.workouttracker.domain.entity.WorkoutLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutLogRepository extends JpaRepository<WorkoutLog, Long> {

    @Query("SELECT wl FROM WorkoutLog wl " +
            "JOIN wl.scheduledWorkout sw " +
            "WHERE sw.user.id = :userId " +
            "ORDER BY wl.performedAt DESC")
    List<WorkoutLog> findAllByUserId(@Param("userId") Long userId);
}
