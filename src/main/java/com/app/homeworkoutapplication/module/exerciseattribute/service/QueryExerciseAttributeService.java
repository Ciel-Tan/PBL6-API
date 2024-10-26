package com.app.homeworkoutapplication.module.exerciseattribute.service;

import com.app.homeworkoutapplication.entity.*;
import com.app.homeworkoutapplication.entity.mapper.ExerciseAttributeMapper;
import com.app.homeworkoutapplication.module.exerciseattribute.dto.ExerciseAttribute;
import com.app.homeworkoutapplication.module.exerciseattribute.service.criteria.ExerciseAttributeCriteria;
import com.app.homeworkoutapplication.repository.ExerciseAttributeRepository;
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
public class QueryExerciseAttributeService extends QueryService<ExerciseAttributeEntity> {

    private final ExerciseAttributeRepository exerciseAttributeRepository;

    private final ExerciseAttributeMapper exerciseAttributeMapper;

    public QueryExerciseAttributeService(ExerciseAttributeRepository exerciseAttributeRepository, ExerciseAttributeMapper exerciseAttributeMapper) {
        this.exerciseAttributeRepository = exerciseAttributeRepository;
        this.exerciseAttributeMapper = exerciseAttributeMapper;
    }

    public List<ExerciseAttribute> findListByCriteria(ExerciseAttributeCriteria criteria) {
        return exerciseAttributeRepository.findAll(createSpecification(criteria)).stream().map(exerciseAttributeMapper::toDto).toList();
    }

    public Page<ExerciseAttribute> findPageByCriteria(ExerciseAttributeCriteria criteria, Pageable pageable) {
        Page<ExerciseAttributeEntity> page = exerciseAttributeRepository.findAll(createSpecification(criteria), pageable);
        return new PageImpl<>(
                page.getContent().stream().map(exerciseAttributeMapper::toDto).toList(),
                pageable,
                page.getTotalElements()
        );
    }

    public ExerciseAttribute findById(Long id) {
        Optional<ExerciseAttributeEntity> exerciseAttributeEntity = exerciseAttributeRepository.findById(id);
        if (exerciseAttributeEntity.isEmpty()) {
            throw new NotFoundException("Not found exercise attribute by id " + id);
        }
        return exerciseAttributeMapper.toDto(exerciseAttributeEntity.get());
    }

    private Specification<ExerciseAttributeEntity> createSpecification(ExerciseAttributeCriteria criteria) {
        Specification<ExerciseAttributeEntity> specification = Specification.where(null);

        if (criteria.getId() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getId(), ExerciseAttributeEntity_.id));
        }
        if (criteria.getAttributeId() != null) {
            specification = specFindByAttributeId(criteria.getAttributeId());
        }
        if (criteria.getExerciseId() != null) {
            specification = specFindByExerciseId(criteria.getExerciseId());
        }
        return specification;
    }

    private Specification<ExerciseAttributeEntity> specFindByAttributeId(LongFilter id) {
        if (id.getEquals() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<ExerciseAttributeEntity, AttributeEntity> join = root.join(ExerciseAttributeEntity_.attribute, JoinType.LEFT);
                return criteriaBuilder.equal(join.get(AttributeEntity_.id), id.getEquals());
            };
        }
        if (id.getIn() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<ExerciseAttributeEntity, AttributeEntity> join = root.join(ExerciseAttributeEntity_.attribute, JoinType.LEFT);
                return join.get(AttributeEntity_.id).in(id.getIn());
            };
        }
        return null;
    }

    private Specification<ExerciseAttributeEntity> specFindByExerciseId(LongFilter id) {
        if (id.getEquals() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<ExerciseAttributeEntity, ExerciseEntity> join = root.join(ExerciseAttributeEntity_.exercise, JoinType.LEFT);
                return criteriaBuilder.equal(join.get(ExerciseEntity_.id), id.getEquals());
            };
        }
        if (id.getIn() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<ExerciseAttributeEntity, ExerciseEntity> join = root.join(ExerciseAttributeEntity_.exercise, JoinType.LEFT);
                return join.get(ExerciseEntity_.id).in(id.getIn());
            };
        }
        return null;
    }
}
