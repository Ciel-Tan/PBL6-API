package com.app.homeworkoutapplication.module.userattribute.service;

import com.app.homeworkoutapplication.entity.*;
import com.app.homeworkoutapplication.entity.mapper.UserAttributeMapper;
import com.app.homeworkoutapplication.module.userattribute.dto.UserAttribute;
import com.app.homeworkoutapplication.module.userattribute.service.criteria.UserAttributeCriteria;
import com.app.homeworkoutapplication.repository.UserAttributeRepository;
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
public class QueryUserAttributeService extends QueryService<UserAttributeEntity> {

    private final UserAttributeRepository userAttributeRepository;

    private final UserAttributeMapper userAttributeMapper;

    public QueryUserAttributeService(UserAttributeRepository userAttributeRepository, UserAttributeMapper userAttributeMapper) {
        this.userAttributeRepository = userAttributeRepository;
        this.userAttributeMapper = userAttributeMapper;
    }

    public List<UserAttribute> findListByCriteria(UserAttributeCriteria criteria) {
        return userAttributeRepository.findAll(createSpecification(criteria)).stream().map(userAttributeMapper::toDto).toList();
    }

    public Page<UserAttribute> findPageByCriteria(UserAttributeCriteria criteria, Pageable pageable) {
        Page<UserAttributeEntity> page = userAttributeRepository.findAll(createSpecification(criteria), pageable);
        return new PageImpl<>(
                page.getContent().stream().map(userAttributeMapper::toDto).toList(),
                pageable,
                page.getTotalElements()
        );
    }

    public UserAttribute findById(Long id) {
        Optional<UserAttributeEntity> exerciseEntity = userAttributeRepository.findById(id);
        if (exerciseEntity.isEmpty()) {
            throw new NotFoundException("Not found exercise by id " + id);
        }
        return userAttributeMapper.toDto(exerciseEntity.get());
    }
    private Specification<UserAttributeEntity> createSpecification(UserAttributeCriteria criteria) {
        Specification<UserAttributeEntity> specification = Specification.where(null);

        if (criteria.getId() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getId(), UserAttributeEntity_.id));
        }
        if (criteria.getName() != null) {
            specification = specification.and(buildStringSpecification(criteria.getName(), UserAttributeEntity_.name));
        }
        if (criteria.getIsFocus() != null) {
            specification = specification.and(buildSpecification(criteria.getIsFocus(), UserAttributeEntity_.isFocus));
        }
        if (criteria.getMeasure() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getMeasure(), UserAttributeEntity_.measure));
        }
        if (criteria.getMeasureGoal() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getMeasureGoal(), UserAttributeEntity_.measureGoal));
        }
        if (criteria.getUnit() != null) {
            specification = specification.and(buildSpecification(criteria.getUnit(), UserAttributeEntity_.unit));
        }
        if (criteria.getUserId() != null) {
            specification = specFindByUserId(criteria.getUserId());
        }
        if (criteria.getAttributeId() != null) {
            specification = specFindByAttributeId(criteria.getAttributeId());
        }

        return specification;
    }

    private Specification<UserAttributeEntity> specFindByUserId(LongFilter userId) {
        if (userId.getEquals() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<UserAttributeEntity, UserEntity> join = root.join(UserAttributeEntity_.user, JoinType.LEFT);
                return criteriaBuilder.equal(join.get(UserEntity_.id), userId.getEquals());
            };
        }
        if (userId.getIn() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<UserAttributeEntity, UserEntity> join = root.join(UserAttributeEntity_.user, JoinType.LEFT);
                return join.get(UserEntity_.id).in(userId.getIn());
            };
        }
        return null;
    }

    private Specification<UserAttributeEntity> specFindByAttributeId(LongFilter userId) {
        if (userId.getEquals() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<UserAttributeEntity, AttributeEntity> join = root.join(UserAttributeEntity_.attribute, JoinType.LEFT);
                return criteriaBuilder.equal(join.get(AttributeEntity_.id), userId.getEquals());
            };
        }
        if (userId.getIn() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<UserAttributeEntity, AttributeEntity> join = root.join(UserAttributeEntity_.attribute, JoinType.LEFT);
                return join.get(AttributeEntity_.id).in(userId.getIn());
            };
        }
        return null;
    }
}
