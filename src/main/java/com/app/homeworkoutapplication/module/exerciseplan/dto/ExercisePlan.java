package com.app.homeworkoutapplication.module.exerciseplan.dto;

import com.app.homeworkoutapplication.module.exercise.dto.Exercise;
import com.app.homeworkoutapplication.module.plan.dto.Plan;
import lombok.Data;

import java.time.Instant;

@Data
public class ExercisePlan {
    private Long id;

    private String time;

    private Long dateOrder;

    private Long setCount;

    private Long repCount;

    private Exercise exercise;

    private Plan plan;
}
