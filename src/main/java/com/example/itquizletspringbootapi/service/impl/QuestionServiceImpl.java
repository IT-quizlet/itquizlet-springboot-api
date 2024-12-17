package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.question.QuestionCreateDto;
import com.example.itquizletspringbootapi.dto.question.QuestionDto;
import com.example.itquizletspringbootapi.dto.question.QuestionUpdateDto;
import com.example.itquizletspringbootapi.repository.QuestionRepository;
import com.example.itquizletspringbootapi.service.QuestionService;
import com.example.itquizletspringbootapi.service.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public QuestionDto addQuestionToQuiz(UUID quizId, QuestionCreateDto question) {
        return null;
    }

    @Override
    public List<QuestionDto> getQuestionsByQuiz(UUID quizId) {
        return List.of();
    }

    @Override
    public QuestionDto updateQuestion(UUID questionId, QuestionUpdateDto updatedQuestion) {
        return null;
    }

    @Override
    public void deleteQuestion(UUID questionId) {

    }

}
