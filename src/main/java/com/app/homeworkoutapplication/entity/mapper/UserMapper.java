package com.app.homeworkoutapplication.entity.mapper;

import com.app.homeworkoutapplication.entity.UserEntity;
import com.app.homeworkoutapplication.module.user.dto.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface UserMapper extends EntityMapper<User, UserEntity> {
}
