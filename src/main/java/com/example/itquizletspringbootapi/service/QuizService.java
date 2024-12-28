package com.example.itquizletspringbootapi.service;

import com.example.itquizletspringbootapi.dto.quiz.QuizCreateDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizUpdateDto;
import com.example.itquizletspringbootapi.repository.entity.Level;
import com.example.itquizletspringbootapi.repository.entity.QuizEntity;
import com.example.itquizletspringbootapi.repository.entity.UserEntity;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface QuizService {

    QuizEntity createQuiz(QuizCreateDto quiz, UserEntity owner);
    QuizEntity getQuizById(UUID quizId);
    List<QuizDto> getAllQuizzes(Level level, List<String> categories);
    List<QuizEntity> getQuizzesByOwner(UUID ownerId);
    QuizEntity updateQuiz(UUID quizId, QuizUpdateDto updatedQuiz, UUID userId) throws BadRequestException;
    void deleteQuiz(UUID quizId, UUID userId) throws BadRequestException;

}
