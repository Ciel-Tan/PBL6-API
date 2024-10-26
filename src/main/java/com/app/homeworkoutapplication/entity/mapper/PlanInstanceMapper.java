package com.app.homeworkoutapplication.entity.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { PlanMapper.class })
public interface PlanInstanceMapper {
}
