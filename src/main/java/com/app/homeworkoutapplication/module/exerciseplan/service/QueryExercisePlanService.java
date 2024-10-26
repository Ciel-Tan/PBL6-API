package com.app.homeworkoutapplication.module.exerciseplan.service;

import com.app.homeworkoutapplication.entity.*;
import com.app.homeworkoutapplication.entity.mapper.ExercisePlanMapper;
import com.app.homeworkoutapplication.module.exerciseplan.dto.ExercisePlan;
import com.app.homeworkoutapplication.module.exerciseplan.service.criteria.ExercisePlanCriteria;
import com.app.homeworkoutapplication.repository.ExercisePlanRepository;
import com.app.homeworkoutapplication.web.rest.error.exception.NotFoundException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;
import tech.jhipster.service.filter.LongFilter;

import java.util.List;
import java.util.Optional;

@Service
public class QueryExercisePlanService extends QueryService<ExercisePlanEntity> {

    private final ExercisePlanRepository exercisePlanRepository;

    private final ExercisePlanMapper exercisePlanMapper;

    public QueryExercisePlanService(ExercisePlanRepository exercisePlanRepository, ExercisePlanMapper exercisePlanMapper) {
        this.exercisePlanRepository = exercisePlanRepository;
        this.exercisePlanMapper = exercisePlanMapper;
    }

    public List<ExercisePlan> findListByCriteria(ExercisePlanCriteria criteria) {
        return exercisePlanRepository.findAll(createSpecification(criteria)).stream().map(exercisePlanMapper::toDto).toList();
    }

    public Page<ExercisePlan> findPageByCriteria(ExercisePlanCriteria criteria, Pageable pageable) {
        Page<ExercisePlanEntity> page = exercisePlanRepository.findAll(createSpecification(criteria), pageable);
        return new PageImpl<>(
                page.getContent().stream().map(exercisePlanMapper::toDto).toList(),
                pageable,
                page.getTotalElements()
        );
    }

    public ExercisePlan findById(Long id) {
        Optional<ExercisePlanEntity> feedbackEntity = exercisePlanRepository.findById(id);
        if (feedbackEntity.isEmpty()) {
            throw new NotFoundException("Not found exercise plan by id " + id);
        }
        return exercisePlanMapper.toDto(feedbackEntity.get());
    }

    private Specification<ExercisePlanEntity> createSpecification(ExercisePlanCriteria criteria) {
        Specification<ExercisePlanEntity> specification = Specification.where(null);

        if (criteria.getId() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getId(), ExercisePlanEntity_.id));
        }
        if (criteria.getRepCount() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getRepCount(), ExercisePlanEntity_.repCount));
        }
        if (criteria.getSetCount() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getSetCount(), ExercisePlanEntity_.setCount));
        }
        if (criteria.getTime() != null) {
            specification = specification.and(buildStringSpecification(criteria.getTime(), ExercisePlanEntity_.time));
        }
        if (criteria.getDateOrder() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getDateOrder(), ExercisePlanEntity_.dateOrder));
        }
        if (criteria.getExerciseId() != null) {
            specification = specFindByExerciseId(criteria.getExerciseId());
        }
        if (criteria.getPlanId() != null) {
            specification = specFindByPlanId(criteria.getPlanId());
        }
        return specification;
    }

    private Specification<ExercisePlanEntity> specFindByExerciseId(LongFilter id) {
        if (id.getEquals() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<ExercisePlanEntity, ExerciseEntity> join = root.join(ExercisePlanEntity_.exercise, JoinType.LEFT);
                return criteriaBuilder.equal(join.get(ExerciseEntity_.id), id.getEquals());
            };
        }
        if (id.getIn() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<ExercisePlanEntity, ExerciseEntity> join = root.join(ExercisePlanEntity_.exercise, JoinType.LEFT);
                return join.get(ExerciseEntity_.id).in(id.getIn());
            };
        }
        return null;
    }

    private Specification<ExercisePlanEntity> specFindByPlanId(LongFilter id) {
        if (id.getEquals() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<ExercisePlanEntity, PlanEntity> join = root.join(ExercisePlanEntity_.plan, JoinType.LEFT);
                return criteriaBuilder.equal(join.get(PlanEntity_.id), id.getEquals());
            };
        }
        if (id.getIn() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<ExercisePlanEntity, PlanEntity> join = root.join(ExercisePlanEntity_.plan, JoinType.LEFT);
                return join.get(PlanEntity_.id).in(id.getIn());
            };
        }
        return null;
    }
}
