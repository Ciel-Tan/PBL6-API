package com.app.homeworkoutapplication.entity.mapper;

import com.app.homeworkoutapplication.entity.FavouriteExerciseEntity;
import com.app.homeworkoutapplication.module.favouriteexercise.dto.FavouriteExercise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class, ExerciseMapper.class })
public interface FavouriteExerciseMapper extends EntityMapper<FavouriteExercise, FavouriteExerciseEntity> {
}
