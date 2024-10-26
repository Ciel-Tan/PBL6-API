package com.app.homeworkoutapplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.Instant;

@Data
@Entity
@Table(name = "exercise_plan_instance")
@ToString
public class ExercisePlanInstanceEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private Instant dateTime;

    @Column(name = "date_order")
    private Long dateOrder;

    @Column(name = "set_count")
    private Long setCount;

    @Column(name = "rep_count")
    private Long repCount;

    @ManyToOne
    private ExerciseEntity exercise;

    @ManyToOne
    private PlanInstanceEntity planInstance;

    @ManyToOne
    private ExercisePlanEntity exercisePlan;
}
