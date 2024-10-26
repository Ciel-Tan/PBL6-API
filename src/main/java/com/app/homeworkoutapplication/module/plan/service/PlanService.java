package com.app.homeworkoutapplication.module.plan.service;

import com.app.homeworkoutapplication.entity.mapper.PlanMapper;
import com.app.homeworkoutapplication.module.exercise.dto.Exercise;
import com.app.homeworkoutapplication.module.plan.dto.Plan;
import com.app.homeworkoutapplication.repository.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlanService {

    private final PlanRepository planRepository;

    private final PlanMapper planMapper;

    public PlanService(PlanRepository planRepository, PlanMapper planMapper) {
        this.planRepository = planRepository;
        this.planMapper = planMapper;
    }

    public Plan save(Plan plan) {
        return planMapper.toDto(planRepository.save(planMapper.toEntity(plan)));
    }

    public void delete(Long id) {
        planRepository.deleteById(id);
    }
}
