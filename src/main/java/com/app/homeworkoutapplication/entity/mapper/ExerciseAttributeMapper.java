package com.app.homeworkoutapplication.entity.mapper;

import com.app.homeworkoutapplication.entity.ExerciseAttributeEntity;
import com.app.homeworkoutapplication.module.exerciseattribute.dto.ExerciseAttribute;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ExerciseMapper.class, AttributeMapper.class })
public interface ExerciseAttributeMapper extends EntityMapper<ExerciseAttribute, ExerciseAttributeEntity> {
}
