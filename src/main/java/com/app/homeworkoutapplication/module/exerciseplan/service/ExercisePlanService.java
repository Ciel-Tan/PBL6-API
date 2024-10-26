package com.app.homeworkoutapplication.module.exerciseplan.service;

import com.app.homeworkoutapplication.entity.mapper.ExercisePlanMapper;
import com.app.homeworkoutapplication.module.exerciseplan.dto.ExercisePlan;
import com.app.homeworkoutapplication.repository.ExercisePlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExercisePlanService {

    private final ExercisePlanRepository exercisePlanRepository;

    private final ExercisePlanMapper exercisePlanMapper;

    public ExercisePlanService(ExercisePlanRepository exercisePlanRepository, ExercisePlanMapper exercisePlanMapper) {
        this.exercisePlanRepository = exercisePlanRepository;
        this.exercisePlanMapper = exercisePlanMapper;
    }

    public ExercisePlan save(ExercisePlan exercisePlan) {
        return exercisePlanMapper.toDto(exercisePlanRepository.save(exercisePlanMapper.toEntity(exercisePlan)));
    }

    public void delete(Long id) {
        exercisePlanRepository.deleteById(id);
    }
}
