package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.quiz.QuizCreateDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizUpdateDto;
import com.example.itquizletspringbootapi.repository.QuizRepository;
import com.example.itquizletspringbootapi.service.QuizService;
import com.example.itquizletspringbootapi.service.mapper.QuizMapper;
import com.example.itquizletspringbootapi.repository.entity.QuizEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    @Override
    public QuizDto createQuiz(QuizCreateDto quiz) {
        QuizEntity quizEntity = quizMapper.toEntity(quiz);
        QuizEntity savedQuiz = quizRepository.save(quizEntity);
        log.info("Quiz created with ID: {}", savedQuiz.getId());
        return quizMapper.toDTO(savedQuiz);
    }

    @Override
    public QuizDto getQuizById(UUID quizId) {
        QuizEntity quizEntity = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + quizId));
        log.info("Retrieved quiz with ID: {}", quizId);
        return quizMapper.toDTO(quizEntity);
    }

    @Override
    public List<QuizDto> getQuizzesByOwner(UUID ownerId) {
        List<QuizEntity> quizzes = quizRepository.findByOwnerId(ownerId);
        log.info("Retrieved {} quizzes for owner ID: {}", quizzes.size(), ownerId);
        return quizzes.stream().map(quizMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public QuizDto updateQuiz(UUID quizId, QuizUpdateDto updatedQuiz) {
        QuizEntity oldEntity = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + quizId));
        quizMapper.updateEntityFromDto(updatedQuiz, oldEntity);
        QuizEntity updatedEntity = quizRepository.save(oldEntity);
        log.info("Updated quiz with ID: {}", quizId);
        return quizMapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteQuiz(UUID quizId) {
        if (!quizRepository.existsById(quizId)) {
            throw new RuntimeException("Quiz not found with ID: " + quizId);
        }
        quizRepository.deleteById(quizId);
        log.info("Deleted quiz with ID: {}", quizId);
    }
}
