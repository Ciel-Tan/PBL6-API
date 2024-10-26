package com.app.homeworkoutapplication.module.feedback.service;

import com.app.homeworkoutapplication.entity.mapper.FeedbackMapper;
import com.app.homeworkoutapplication.module.feedback.dto.Feedback;
import com.app.homeworkoutapplication.repository.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    private final FeedbackMapper feedbackMapper;

    public FeedbackService(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }

    public Feedback save(Feedback exercise) {
        return feedbackMapper.toDto(feedbackRepository.save(feedbackMapper.toEntity(exercise)));
    }

    public void delete(Long id) {
        feedbackRepository.deleteById(id);
    }
}
