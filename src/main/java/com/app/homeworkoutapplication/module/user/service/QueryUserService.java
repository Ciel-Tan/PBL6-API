package com.app.homeworkoutapplication.module.user.service;

import com.app.homeworkoutapplication.entity.UserEntity;
import com.app.homeworkoutapplication.entity.UserEntity_;
import com.app.homeworkoutapplication.entity.mapper.UserMapper;
import com.app.homeworkoutapplication.module.user.dto.User;
import com.app.homeworkoutapplication.module.user.service.criteria.UserCriteria;
import com.app.homeworkoutapplication.repository.UserRepository;
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
public class QueryUserService extends QueryService<UserEntity> {

    private final UserRepository userRepository;


    private final UserMapper userMapper;

    public QueryUserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> findListByCriteria(UserCriteria criteria) {
        return userRepository.findAll(createSpecification(criteria)).stream().map(userEntity -> {
            return userMapper.toDto(userEntity);
        }).toList();
    }

    public Page<User> findPageByCriteria(UserCriteria criteria, Pageable pageable) {
        Page<UserEntity> page =  userRepository.findAll(createSpecification(criteria), pageable);
        return new PageImpl<>(
            page.getContent().stream().map(userEntity -> {
                return userMapper.toDto(userEntity);
            }).toList(),
            pageable,
            page.getTotalElements()
        );
    }

    public User findById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) {
            throw new NotFoundException("Not found user by id " + id);
        }
        User user = userMapper.toDto(userEntity.get());
        return user;
    }

    public User getByUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if (userEntity.isEmpty()) {
            throw new NotFoundException("Not found user by username " + username);
        }
        User user = userMapper.toDto(userEntity.get());
        return user;
    }


    private Specification<UserEntity> createSpecification(UserCriteria criteria) {
        Specification<UserEntity> specification = Specification.where(null);

        if (criteria.getId() != null) {
            specification = specification.and(buildRangeSpecification(criteria.getId(), UserEntity_.id));
        }
        if (criteria.getEmail() != null) {
            specification = specification.and(buildStringSpecification(criteria.getEmail(), UserEntity_.email));
        }
        if (criteria.getFirstName() != null) {
            specification = specification.and(buildStringSpecification(criteria.getFirstName(), UserEntity_.firstName));
        }
        if (criteria.getLastName() != null) {
            specification = specification.and(buildStringSpecification(criteria.getLastName(), UserEntity_.lastName));
        }
        if (criteria.getUsername() != null) {
            specification = specification.and(buildStringSpecification(criteria.getUsername(), UserEntity_.username));
        }
        if (criteria.getBirthday() != null) {
            specification = specification.and(buildSpecification(criteria.getBirthday(), UserEntity_.birthday));
        }
        if (criteria.getLevel() != null) {
            specification = specification.and(buildSpecification(criteria.getLevel(), UserEntity_.level));
        }

        return specification;
    }
}
