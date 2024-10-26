package com.app.homeworkoutapplication.module.plan.service;

import com.app.homeworkoutapplication.entity.*;
import com.app.homeworkoutapplication.entity.enumeration.PlanStatus;
import com.app.homeworkoutapplication.entity.filter.PlanStatusFilter;
import com.app.homeworkoutapplication.entity.mapper.PlanMapper;
import com.app.homeworkoutapplication.module.plan.dto.Plan;
import com.app.homeworkoutapplication.module.plan.service.criteria.PlanCriteria;
import com.app.homeworkoutapplication.repository.PlanRepository;
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
import tech.jhipster.service.filter.StringFilter;

import java.util.List;
import java.util.Optional;

@Service
public class QueryPlanService extends QueryService<PlanEntity> {

    private final PlanRepository planRepository;

    private final PlanMapper planMapper;

    public QueryPlanService(PlanRepository planRepository, PlanMapper planMapper) {
        this.planRepository = planRepository;
        this.planMapper = planMapper;
    }

    public Page<Plan> findPublicPageByCriteria(PlanCriteria criteria, Pageable pageable) {
        PlanStatusFilter statusFilter = new PlanStatusFilter();
        statusFilter.setEquals(PlanStatus.PUBLIC);
        criteria.setStatus(statusFilter);
        Page<PlanEntity> page = planRepository.findAll(createSpecification(criteria), pageable);
        return new PageImpl<>(
                page.getContent().stream().map(planMapper::toDto).toList(),
                pageable,
                page.getTotalElements()
        );
    }

    public List<Plan> findPublicListByCriteria(PlanCriteria criteria) {
        PlanStatusFilter statusFilter = new PlanStatusFilter();
        statusFilter.setEquals(PlanStatus.PUBLIC);
        criteria.setStatus(statusFilter);
        return planRepository.findAll(createSpecification(criteria)).stream().map(planMapper::toDto).toList();
    }

    public List<Plan> findListByCriteria(PlanCriteria criteria) {
        return planRepository.findAll(createSpecification(criteria)).stream().map(planMapper::toDto).toList();
    }

    public Page<Plan> findPageByCriteria(PlanCriteria criteria, Pageable pageable) {
        Page<PlanEntity> page = planRepository.findAll(createSpecification(criteria), pageable);
        return new PageImpl<>(
                page.getContent().stream().map(planMapper::toDto).toList(),
                pageable,
                page.getTotalElements()
        );
    }

    public Plan findById(Long id) {
        Optional<PlanEntity> planEntity = planRepository.findById(id);
        if (planEntity.isEmpty()) {
            throw new NotFoundException("Not found plan by id " + id);
        }
        return planMapper.toDto(planEntity.get());
    }

    private Specification<PlanEntity> createSpecification(PlanCriteria criteria) {
        Specification<PlanEntity> specification = Specification.where(null);

        if (criteria.getId() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getId(), PlanEntity_.id));
        }
        if (criteria.getName() != null) {
            specification = specification.and(buildStringSpecification(criteria.getName(), PlanEntity_.name));
        }
        if (criteria.getDescription() != null) {
            specification = specification.and(buildStringSpecification(criteria.getDescription(), PlanEntity_.description));
        }
        if (criteria.getRating() != null) {
            specification = specification.and(buildSpecification(criteria.getRating(), PlanEntity_.rating));
        }
        if (criteria.getStatus() != null) {
            specification = specification.and(buildSpecification(criteria.getStatus(), PlanEntity_.status));
        }
        if (criteria.getTotalDays() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getTotalDays(), PlanEntity_.totalDays));
        }
        if (criteria.getUserId() != null) {
            specification = specFindByUserId(criteria.getUserId());
        }
        return specification;
    }

    private Specification<PlanEntity> specFindByUserId(LongFilter userId) {
        if (userId.getEquals() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<PlanEntity, UserEntity> join = root.join(PlanEntity_.user, JoinType.LEFT);
                return criteriaBuilder.equal(join.get(UserEntity_.id), userId.getEquals());
            };
        }
        if (userId.getIn() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<PlanEntity, UserEntity> join = root.join(PlanEntity_.user, JoinType.LEFT);
                return join.get(UserEntity_.id).in(userId.getIn());
            };
        }
        return null;
    }
}
