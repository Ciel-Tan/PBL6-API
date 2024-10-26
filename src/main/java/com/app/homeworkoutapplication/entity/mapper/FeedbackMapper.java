package com.app.homeworkoutapplication.entity.mapper;

import com.app.homeworkoutapplication.entity.FeedbackEntity;
import com.app.homeworkoutapplication.module.feedback.dto.Feedback;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class, PlanMapper.class })
public interface FeedbackMapper extends EntityMapper<Feedback, FeedbackEntity> {
}
