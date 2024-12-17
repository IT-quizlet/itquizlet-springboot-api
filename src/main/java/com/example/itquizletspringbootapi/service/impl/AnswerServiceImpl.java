package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerCreateDto;
import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerDto;
import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerUpdateDto;
import com.example.itquizletspringbootapi.repository.AnswerRepository;
import com.example.itquizletspringbootapi.service.AnswerService;
import com.example.itquizletspringbootapi.service.mapper.QuestionAnswerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionAnswerMapper questionAnswerMapper;

    @Override
    public QuestionAnswerDto addAnswerToQuestion(UUID questionId, QuestionAnswerCreateDto answer) {
        return null;
    }

    @Override
    public List<QuestionAnswerDto> getAnswersByQuestion(UUID questionId) {
        return List.of();
    }

    @Override
    public QuestionAnswerDto updateAnswer(UUID answerId, QuestionAnswerUpdateDto updatedAnswer) {
        return null;
    }

    @Override
    public void deleteAnswer(UUID answerId) {

    }

}
