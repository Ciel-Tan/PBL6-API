package com.app.homeworkoutapplication.entity.mapper;

import com.app.homeworkoutapplication.entity.PlanEntity;
import com.app.homeworkoutapplication.module.plan.dto.Plan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { })
public interface PlanMapper extends EntityMapper<Plan, PlanEntity> {
}
