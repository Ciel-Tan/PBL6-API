package com.app.homeworkoutapplication.module.user.service;

import com.app.homeworkoutapplication.entity.mapper.UserMapper;
import com.app.homeworkoutapplication.module.user.dto.User;
import com.app.homeworkoutapplication.repository.UserRepository;
import com.app.homeworkoutapplication.web.rest.error.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User save(User user) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
