package com.app.homeworkoutapplication.module.exercise.service;

import com.app.homeworkoutapplication.entity.*;
import com.app.homeworkoutapplication.entity.mapper.ExerciseMapper;
import com.app.homeworkoutapplication.module.exercise.dto.Exercise;
import com.app.homeworkoutapplication.module.exercise.service.criteria.ExerciseCriteria;
import com.app.homeworkoutapplication.repository.ExerciseRepository;
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
public class QueryExerciseService extends QueryService<ExerciseEntity> {

    private final ExerciseRepository exerciseRepository;

    private final ExerciseMapper exerciseMapper;

    public QueryExerciseService(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    public List<Exercise> findListByCriteria(ExerciseCriteria criteria) {
        return exerciseRepository.findAll(createSpecification(criteria)).stream().map(exerciseMapper::toDto).toList();
    }

    public Page<Exercise> findPageByCriteria(ExerciseCriteria criteria, Pageable pageable) {
        Page<ExerciseEntity> page = exerciseRepository.findAll(createSpecification(criteria), pageable);
        return new PageImpl<>(
                page.getContent().stream().map(exerciseMapper::toDto).toList(),
                pageable,
                page.getTotalElements()
        );
    }

    public Exercise findById(Long id) {
        Optional<ExerciseEntity> exerciseEntity = exerciseRepository.findById(id);
        if (exerciseEntity.isEmpty()) {
            throw new NotFoundException("Not found exercise by id " + id);
        }
        return exerciseMapper.toDto(exerciseEntity.get());
    }

    private Specification<ExerciseEntity> createSpecification(ExerciseCriteria criteria) {
        Specification<ExerciseEntity> specification = Specification.where(null);

        if (criteria.getId() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getId(), ExerciseEntity_.id));
        }
        if (criteria.getName() != null) {
            specification = specification.and(buildStringSpecification(criteria.getName(), ExerciseEntity_.name));
        }
        if (criteria.getUserId() != null) {
            specification = specFindByUserId(criteria.getUserId());
        }
        return specification;
    }

    private Specification<ExerciseEntity> specFindByUserId(LongFilter userId) {
        if (userId.getEquals() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<ExerciseEntity, UserEntity> join = root.join(ExerciseEntity_.user, JoinType.LEFT);
                return criteriaBuilder.equal(join.get(UserEntity_.id), userId.getEquals());
            };
        }
        if (userId.getIn() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<ExerciseEntity, UserEntity> join = root.join(ExerciseEntity_.user, JoinType.LEFT);
                return join.get(UserEntity_.id).in(userId.getIn());
            };
        }
        return null;
    }
}
