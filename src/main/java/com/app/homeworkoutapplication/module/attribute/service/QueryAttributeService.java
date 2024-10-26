package com.app.homeworkoutapplication.module.attribute.service;

import com.app.homeworkoutapplication.entity.AttributeEntity;
import com.app.homeworkoutapplication.entity.AttributeEntity_;
import com.app.homeworkoutapplication.entity.mapper.AttributeMapper;
import com.app.homeworkoutapplication.module.attribute.dto.Attribute;
import com.app.homeworkoutapplication.module.attribute.service.criteria.AttributeCriteria;
import com.app.homeworkoutapplication.repository.AttributeRepository;
import com.app.homeworkoutapplication.web.rest.error.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;

import java.util.List;
import java.util.Optional;

@Service
public class QueryAttributeService extends QueryService<AttributeEntity> {

    private final AttributeRepository attributeRepository;

    private final AttributeMapper attributeMapper;

    public QueryAttributeService(AttributeRepository attributeRepository, AttributeMapper attributeMapper) {
        this.attributeRepository = attributeRepository;
        this.attributeMapper = attributeMapper;
    }

    public List<Attribute> findListByCriteria(AttributeCriteria criteria) {
        return attributeRepository.findAll(createSpecification(criteria)).stream().map(attributeMapper::toDto).toList();
    }

    public Page<Attribute> findPageByCriteria(AttributeCriteria criteria, Pageable pageable) {
        Page<AttributeEntity> page = attributeRepository.findAll(createSpecification(criteria), pageable);
        return new PageImpl<>(
                page.getContent().stream().map(attributeMapper::toDto).toList(),
                pageable,
                page.getTotalElements()
        );
    }

    public Attribute findById(Long id) {
        Optional<AttributeEntity> attributeEntity = attributeRepository.findById(id);
        if (attributeEntity.isEmpty()) {
            throw new NotFoundException("Not found attribute by id " + id);
        }
        return attributeMapper.toDto(attributeEntity.get());
    }

    private Specification<AttributeEntity> createSpecification(AttributeCriteria criteria) {
        Specification<AttributeEntity> specification = Specification.where(null);

        if (criteria.getId() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getId(), AttributeEntity_.id));
        }
        if (criteria.getName() != null) {
            specification = specification.and(buildStringSpecification(criteria.getName(), AttributeEntity_.name));
        }
        return specification;
    }
}
