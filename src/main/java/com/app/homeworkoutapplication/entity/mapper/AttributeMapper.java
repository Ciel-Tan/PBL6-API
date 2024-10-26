package com.app.homeworkoutapplication.entity.mapper;

import com.app.homeworkoutapplication.entity.AttributeEntity;
import com.app.homeworkoutapplication.module.attribute.dto.Attribute;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface AttributeMapper extends EntityMapper<Attribute, AttributeEntity> {
}
