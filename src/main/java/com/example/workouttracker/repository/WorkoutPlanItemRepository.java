package com.example.workouttracker.repository;

import com.example.workouttracker.domain.entity.WorkoutPlanItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutPlanItemRepository extends JpaRepository<WorkoutPlanItem, Long> {

    List<WorkoutPlanItem> findAllByPlanIdOrderByItemOrderAsc(Long planId);

    void deleteAllByPlanId(Long planId);
}
