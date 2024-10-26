package com.app.homeworkoutapplication.module.account.service;

import com.app.homeworkoutapplication.entity.RoleEntity;
import com.app.homeworkoutapplication.entity.UserEntity;
import com.app.homeworkoutapplication.entity.mapper.UserMapper;
import com.app.homeworkoutapplication.module.account.dto.RegisterRequest;
import com.app.homeworkoutapplication.module.mail.service.MailService;
import com.app.homeworkoutapplication.module.role.service.QueryRoleService;
import com.app.homeworkoutapplication.module.user.dto.User;
import com.app.homeworkoutapplication.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private final MailService mailService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final QueryRoleService queryRoleService;

    public AccountService(MailService mailService, UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, QueryRoleService queryRoleService) {
        this.mailService = mailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.queryRoleService = queryRoleService;
    }

    public User register(RegisterRequest registerRequest, String roles) {

        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        UserEntity newUser = new UserEntity();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(encodedPassword);
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(queryRoleService.getByName(roles).getId());
        newUser.setRole(roleEntity);
        newUser.setEmail(registerRequest.getEmail());
        newUser.setIsActivated(false);
        User user =  userMapper.toDto(userRepository.save(newUser));
        mailService.sendActivationEmail(user);
        return user;
    }

    public void activateUser(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.setIsActivated(true);
            userRepository.save(user);
        }
    }
}
