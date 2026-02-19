package com.example.workouttracker.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "workout_plan_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutPlanItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private WorkoutPlan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "sets_count", nullable = false)
    private Integer setsCount;

    @Column(name = "reps_count", nullable = false)
    private Integer repsCount;

    @Column(name = "target_weight", precision = 5, scale = 2)
    private BigDecimal targetWeight;

    @Column(name = "rest_seconds")
    private Integer restSeconds;

    @Column(name = "item_order", nullable = false)
    private Integer itemOrder;
}
