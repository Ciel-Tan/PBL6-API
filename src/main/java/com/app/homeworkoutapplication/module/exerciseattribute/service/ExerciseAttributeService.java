package com.app.homeworkoutapplication.module.exerciseattribute.service;

import com.app.homeworkoutapplication.entity.mapper.ExerciseAttributeMapper;
import com.app.homeworkoutapplication.module.exerciseattribute.dto.ExerciseAttribute;
import com.app.homeworkoutapplication.repository.ExerciseAttributeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExerciseAttributeService {

    private final ExerciseAttributeRepository exerciseAttributeRepository;

    private final ExerciseAttributeMapper exerciseAttributeMapper;


    public ExerciseAttributeService(ExerciseAttributeRepository exerciseAttributeRepository, ExerciseAttributeMapper exerciseAttributeMapper) {
        this.exerciseAttributeRepository = exerciseAttributeRepository;
        this.exerciseAttributeMapper = exerciseAttributeMapper;
    }

    public ExerciseAttribute save(ExerciseAttribute exercise) {
        return exerciseAttributeMapper.toDto(exerciseAttributeRepository.save(exerciseAttributeMapper.toEntity(exercise)));
    }

    public void delete(Long id) {
        exerciseAttributeRepository.deleteById(id);
    }
}
