package com.app.homeworkoutapplication.module.userattribute.service;

import com.app.homeworkoutapplication.entity.mapper.UserAttributeMapper;
import com.app.homeworkoutapplication.module.userattribute.dto.UserAttribute;
import com.app.homeworkoutapplication.repository.UserAttributeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAttributeService {

    private final UserAttributeRepository userAttributeRepository;

    private final UserAttributeMapper userAttributeMapper;

    public UserAttributeService(UserAttributeRepository userAttributeRepository, UserAttributeMapper userAttributeMapper) {
        this.userAttributeRepository = userAttributeRepository;
        this.userAttributeMapper = userAttributeMapper;
    }

    public UserAttribute save(UserAttribute userAttribute) {
        return userAttributeMapper.toDto(userAttributeRepository.save(userAttributeMapper.toEntity(userAttribute)));
    }

    public void delete(Long id) {
        userAttributeRepository.deleteById(id);
    }
}
