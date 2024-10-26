package com.app.homeworkoutapplication.entity.mapper;

import com.app.homeworkoutapplication.entity.UserAttributeEntity;
import com.app.homeworkoutapplication.module.userattribute.dto.UserAttribute;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class, AttributeMapper.class })
public interface UserAttributeMapper extends EntityMapper<UserAttribute, UserAttributeEntity> {
}
