package com.example.itquizletspringbootapi.service;

import com.example.itquizletspringbootapi.dto.question.QuestionCreateDto;
import com.example.itquizletspringbootapi.dto.question.QuestionDto;
import com.example.itquizletspringbootapi.dto.question.QuestionUpdateDto;

import java.util.List;
import java.util.UUID;

public interface QuestionService {

    QuestionDto addQuestionToQuiz(UUID quizId, QuestionCreateDto question);
    QuestionDto getQuestionById(UUID questionId);
    List<QuestionDto> getQuestionsByQuiz(UUID quizId);
    QuestionDto updateQuestion(UUID questionId, QuestionUpdateDto updatedQuestion);
    void deleteQuestion(UUID questionId);

}
