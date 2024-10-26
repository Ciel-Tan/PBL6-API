package com.app.homeworkoutapplication.module.exercise.dto;

import lombok.Data;

@Data
public class Exercise {

    private Long id;

    private String name;

    private String description;

    private String videoPath;
}
