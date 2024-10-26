package com.app.homeworkoutapplication.repository;

import com.app.homeworkoutapplication.entity.UserAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAttributeRepository extends JpaRepository<UserAttributeEntity, Long>, JpaSpecificationExecutor<UserAttributeEntity> {
}
