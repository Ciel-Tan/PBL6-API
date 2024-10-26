package com.app.homeworkoutapplication.module.feedback.service;

import com.app.homeworkoutapplication.entity.*;
import com.app.homeworkoutapplication.entity.mapper.FeedbackMapper;
import com.app.homeworkoutapplication.module.feedback.dto.Feedback;
import com.app.homeworkoutapplication.module.feedback.service.criteria.FeedbackCriteria;
import com.app.homeworkoutapplication.repository.FeedbackRepository;
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
public class QueryFeedbackService extends QueryService<FeedbackEntity> {

    private final FeedbackRepository feedbackRepository;

    private final FeedbackMapper feedbackMapper;

    public QueryFeedbackService(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }


    public List<Feedback> findListByCriteria(FeedbackCriteria criteria) {
        return feedbackRepository.findAll(createSpecification(criteria)).stream().map(feedbackMapper::toDto).toList();
    }

    public Page<Feedback> findPageByCriteria(FeedbackCriteria criteria, Pageable pageable) {
        Page<FeedbackEntity> page = feedbackRepository.findAll(createSpecification(criteria), pageable);
        return new PageImpl<>(
                page.getContent().stream().map(feedbackMapper::toDto).toList(),
                pageable,
                page.getTotalElements()
        );
    }

    public Feedback findById(Long id) {
        Optional<FeedbackEntity> feedbackEntity = feedbackRepository.findById(id);
        if (feedbackEntity.isEmpty()) {
            throw new NotFoundException("Not found feedback by id " + id);
        }
        return feedbackMapper.toDto(feedbackEntity.get());
    }

    private Specification<FeedbackEntity> createSpecification(FeedbackCriteria criteria) {
        Specification<FeedbackEntity> specification = Specification.where(null);

        if (criteria.getId() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getId(), FeedbackEntity_.id));
        }
        if (criteria.getComment() != null) {
            specification = specification.and(buildStringSpecification(criteria.getComment(), FeedbackEntity_.comment));
        }
        if (criteria.getUserId() != null) {
            specification = specFindByUserId(criteria.getUserId());
        }
        if (criteria.getPlanId() != null) {
            specification = specFindByPlanId(criteria.getPlanId());
        }
        return specification;
    }

    private Specification<FeedbackEntity> specFindByUserId(LongFilter id) {
        if (id.getEquals() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<FeedbackEntity, UserEntity> join = root.join(FeedbackEntity_.user, JoinType.LEFT);
                return criteriaBuilder.equal(join.get(UserEntity_.id), id.getEquals());
            };
        }
        if (id.getIn() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<FeedbackEntity, UserEntity> join = root.join(FeedbackEntity_.user, JoinType.LEFT);
                return join.get(UserEntity_.id).in(id.getIn());
            };
        }
        return null;
    }

    private Specification<FeedbackEntity> specFindByPlanId(LongFilter id) {
        if (id.getEquals() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<FeedbackEntity, PlanEntity> join = root.join(FeedbackEntity_.plan, JoinType.LEFT);
                return criteriaBuilder.equal(join.get(PlanEntity_.id), id.getEquals());
            };
        }
        if (id.getIn() != null) {
            return (root, query, criteriaBuilder) -> {
                Join<FeedbackEntity, PlanEntity> join = root.join(FeedbackEntity_.plan, JoinType.LEFT);
                return join.get(PlanEntity_.id).in(id.getIn());
            };
        }
        return null;
    }
}
