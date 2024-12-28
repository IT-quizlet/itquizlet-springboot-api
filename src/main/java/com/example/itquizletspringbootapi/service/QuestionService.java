package com.example.itquizletspringbootapi.service;

import com.example.itquizletspringbootapi.dto.question.QuestionCreateDto;
import com.example.itquizletspringbootapi.dto.question.QuestionDto;
import com.example.itquizletspringbootapi.dto.question.QuestionUpdateDto;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface QuestionService {

    void checkIfQuestionBelongsToQuiz(UUID questionId, UUID quizId) throws BadRequestException;
    QuestionDto addQuestionToQuiz(UUID quizId, QuestionCreateDto question) throws BadRequestException;
    QuestionDto getQuestionById(UUID questionId) throws BadRequestException;
    List<QuestionDto> getQuestionsByQuiz(UUID quizId);
    QuestionDto updateQuestion(UUID questionId, QuestionUpdateDto updatedQuestion) throws BadRequestException;
    void deleteQuestion(UUID questionId) throws BadRequestException;

}
