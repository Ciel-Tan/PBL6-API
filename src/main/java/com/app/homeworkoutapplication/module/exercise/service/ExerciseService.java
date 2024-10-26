package com.app.homeworkoutapplication.module.exercise.service;

import com.app.homeworkoutapplication.entity.mapper.ExerciseMapper;
import com.app.homeworkoutapplication.module.exercise.dto.Exercise;
import com.app.homeworkoutapplication.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final ExerciseMapper exerciseMapper;

    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    public Exercise save(Exercise exercise) {
        return exerciseMapper.toDto(exerciseRepository.save(exerciseMapper.toEntity(exercise)));
    }

    public void delete(Long id) {
        exerciseRepository.deleteById(id);
    }
}
