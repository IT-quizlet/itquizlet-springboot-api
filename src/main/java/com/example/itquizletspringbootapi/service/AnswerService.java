package com.example.itquizletspringbootapi.service;

import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerCreateDto;
import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerDto;
import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerUpdateDto;

import java.util.List;
import java.util.UUID;

public interface AnswerService {

    QuestionAnswerDto addAnswerToQuestion(UUID questionId, QuestionAnswerCreateDto answer);

    QuestionAnswerDto getAnswerById(UUID id);
    List<QuestionAnswerDto> getAnswersByQuestion(UUID questionId);
    QuestionAnswerDto updateAnswer(UUID answerId, QuestionAnswerUpdateDto updatedAnswer);
    void deleteAnswer(UUID answerId);

}
