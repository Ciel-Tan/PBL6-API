package com.app.homeworkoutapplication.repository;

import com.app.homeworkoutapplication.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeEntity, Long>, JpaSpecificationExecutor<AttributeEntity> {
}
