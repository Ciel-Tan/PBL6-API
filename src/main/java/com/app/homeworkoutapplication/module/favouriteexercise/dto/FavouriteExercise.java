package com.app.homeworkoutapplication.module.favouriteexercise.dto;

import com.app.homeworkoutapplication.module.exercise.dto.Exercise;
import com.app.homeworkoutapplication.module.user.dto.User;
import lombok.Data;

@Data
public class FavouriteExercise {
    private Long id;

    private User user;

    private Exercise exercise;
}
