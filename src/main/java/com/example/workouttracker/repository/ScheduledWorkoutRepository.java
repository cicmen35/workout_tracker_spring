package com.example.workouttracker.repository;

import com.example.workouttracker.domain.entity.ScheduledWorkout;
import com.example.workouttracker.domain.enums.WorkoutStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduledWorkoutRepository extends JpaRepository<ScheduledWorkout, Long> {

    List<ScheduledWorkout> findAllByUserIdAndStatus(Long userId, WorkoutStatus status);

    List<ScheduledWorkout> findAllByUserIdAndStatusOrderByScheduledAtAsc(Long userId, WorkoutStatus status);

    List<ScheduledWorkout> findAllByUserId(Long userId);

    List<ScheduledWorkout> findAllByUserIdOrderByScheduledAtDesc(Long userId);

    Optional<ScheduledWorkout> findByIdAndUserId(Long id, Long userId);
}
