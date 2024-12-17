package com.example.itquizletspringbootapi.service;

import com.example.itquizletspringbootapi.dto.quiz.QuizCreateDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizUpdateDto;

import java.util.List;
import java.util.UUID;

public interface QuizService {

    QuizDto createQuiz(QuizCreateDto quiz);
    QuizDto getQuizById(UUID quizId);
    List<QuizDto> getQuizzesByOwner(UUID ownerId);
    QuizDto updateQuiz(UUID quizId, QuizUpdateDto updatedQuiz);
    void deleteQuiz(UUID quizId);

}
