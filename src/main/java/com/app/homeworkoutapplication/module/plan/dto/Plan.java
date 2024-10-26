package com.app.homeworkoutapplication.module.plan.dto;


import com.app.homeworkoutapplication.entity.enumeration.PlanStatus;
import lombok.Data;

@Data
public class Plan {

    private Long id;

    private String name;

    private String description;

    private PlanStatus status;

    private Integer totalDays;

    private Float rating;
}
