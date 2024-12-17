package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.quiz.QuizCreateDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizUpdateDto;
import com.example.itquizletspringbootapi.repository.QuizRepository;
import com.example.itquizletspringbootapi.service.QuizService;
import com.example.itquizletspringbootapi.service.mapper.QuizMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    @Override
    public QuizDto createQuiz(QuizCreateDto quiz) {
        return null;
    }

    @Override
    public QuizDto getQuizById(UUID quizId) {
        return null;
    }

    @Override
    public List<QuizDto> getQuizzesByOwner(UUID ownerId) {
        return List.of();
    }

    @Override
    public QuizDto updateQuiz(UUID quizId, QuizUpdateDto updatedQuiz) {
        return null;
    }

    @Override
    public void deleteQuiz(UUID quizId) {

    }

}
