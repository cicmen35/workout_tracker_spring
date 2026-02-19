package com.example.workouttracker.repository;

import com.example.workouttracker.domain.entity.WorkoutLogItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutLogItemRepository extends JpaRepository<WorkoutLogItem, Long> {

    @Query("SELECT wli FROM WorkoutLogItem wli " +
            "JOIN wli.log wl " +
            "JOIN wl.scheduledWorkout sw " +
            "WHERE sw.user.id = :userId AND wli.exercise.id = :exerciseId " +
            "ORDER BY wl.performedAt ASC")
    List<WorkoutLogItem> findAllByUserIdAndExerciseId(
            @Param("userId") Long userId,
            @Param("exerciseId") Long exerciseId);
}
