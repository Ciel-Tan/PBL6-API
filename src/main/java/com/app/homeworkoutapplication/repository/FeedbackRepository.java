package com.app.homeworkoutapplication.repository;

import com.app.homeworkoutapplication.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long>, JpaSpecificationExecutor<FeedbackEntity> {
}
