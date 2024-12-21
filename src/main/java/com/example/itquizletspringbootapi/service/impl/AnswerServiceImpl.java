package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerCreateDto;
import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerDto;
import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerUpdateDto;
import com.example.itquizletspringbootapi.repository.AnswerRepository;
import com.example.itquizletspringbootapi.repository.QuestionRepository;
import com.example.itquizletspringbootapi.repository.entity.QuestionAnswerEntity;
import com.example.itquizletspringbootapi.repository.entity.QuestionEntity;
import com.example.itquizletspringbootapi.service.AnswerService;
import com.example.itquizletspringbootapi.service.mapper.QuestionAnswerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionAnswerMapper questionAnswerMapper;
    private final QuestionRepository questionRepository;

    @Override
    public QuestionAnswerDto addAnswerToQuestion(UUID questionId, QuestionAnswerCreateDto questionAnswerCreateDto) {
        QuestionEntity questionEntity = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with ID: " + questionId));

        QuestionAnswerEntity questionAnswerEntity = questionAnswerMapper.toEntity(questionAnswerCreateDto);
        questionAnswerEntity.setQuestion(questionEntity);

        QuestionAnswerEntity savedEntity = answerRepository.save(questionAnswerEntity);
        log.info("Answer created with ID: {}", savedEntity.getId());
        return questionAnswerMapper.toDto(savedEntity);
    }

    @Override
    public QuestionAnswerDto getAnswerById(UUID id) {
        QuestionAnswerEntity questionAnswerEntity = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found with ID: " + id));
        log.info("Retrieved answer with ID: {}", id);
        return questionAnswerMapper.toDto(questionAnswerEntity);
    }

    @Override
    public List<QuestionAnswerDto> getAnswersByQuestion(UUID questionId) {
        List<QuestionAnswerEntity> answers = answerRepository.findByQuestionId(questionId);
        log.info("Retrieved {} answers for question with ID: {}", answers.size(), questionId);
        return answers.stream()
                .map(questionAnswerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionAnswerDto updateAnswer(UUID answerId, QuestionAnswerUpdateDto updatedAnswerDto) {
        QuestionAnswerEntity questionAnswerEntity = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found with ID: " + answerId));
        questionAnswerMapper.updateEntityFromDto(updatedAnswerDto, questionAnswerEntity);
        QuestionAnswerEntity updatedEntity = answerRepository.save(questionAnswerEntity);
        log.info("Updated answer with ID: {}", answerId);
        return questionAnswerMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteAnswer(UUID answerId) {
        if(!answerRepository.existsById(answerId)) {
            throw new RuntimeException("Answer not found with ID: " + answerId);
        }
        answerRepository.deleteById(answerId);
        log.info("Deleted answer with ID: {}", answerId);
    }

}
