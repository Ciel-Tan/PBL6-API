package com.app.homeworkoutapplication.module.role.service;

import com.app.homeworkoutapplication.entity.mapper.RoleMapper;
import com.app.homeworkoutapplication.module.role.dto.Role;
import com.app.homeworkoutapplication.repository.RoleRepository;
import com.app.homeworkoutapplication.web.rest.error.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public Role create(Role role) {
        if (role.getId() != null) {
            throw new BadRequestException("id not null!");
        }
        return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(role)));
    }

    public Role update(Role role) {
        if (role.getId() == null) {
            throw new BadRequestException("id null!");
        }
        return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(role)));
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
