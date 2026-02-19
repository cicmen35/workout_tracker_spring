package com.example.workouttracker.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "workout_log_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutLogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id", nullable = false)
    private WorkoutLog log;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "sets_done", nullable = false)
    private Integer setsDone;

    @Column(name = "reps_done", nullable = false)
    private Integer repsDone;

    @Column(name = "weight_used", precision = 5, scale = 2)
    private BigDecimal weightUsed;

    @Column
    private Integer rpe;

    @Column(name = "item_order", nullable = false)
    private Integer itemOrder;
}
