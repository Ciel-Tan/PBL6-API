package com.app.homeworkoutapplication.module.attribute.service;

import com.app.homeworkoutapplication.entity.mapper.AttributeMapper;
import com.app.homeworkoutapplication.module.attribute.dto.Attribute;
import com.app.homeworkoutapplication.repository.AttributeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AttributeService {

    private final AttributeRepository attributeRepository;

    private final AttributeMapper attributeMapper;

    public AttributeService(AttributeRepository attributeRepository, AttributeMapper attributeMapper) {
        this.attributeRepository = attributeRepository;
        this.attributeMapper = attributeMapper;
    }

    public Attribute save(Attribute attribute) {
        return attributeMapper.toDto(attributeRepository.save(attributeMapper.toEntity(attribute)));
    }

    public void delete(Long id) {
        attributeRepository.deleteById(id);
    }
}
