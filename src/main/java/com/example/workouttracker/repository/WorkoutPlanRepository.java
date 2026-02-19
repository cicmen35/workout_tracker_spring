package com.example.workouttracker.repository;

import com.example.workouttracker.domain.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {

    List<WorkoutPlan> findAllByUserId(Long userId);

    Optional<WorkoutPlan> findByIdAndUserId(Long id, Long userId);
}
