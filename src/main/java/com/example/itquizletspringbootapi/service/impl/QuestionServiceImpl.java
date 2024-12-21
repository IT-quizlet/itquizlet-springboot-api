package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.question.QuestionCreateDto;
import com.example.itquizletspringbootapi.dto.question.QuestionDto;
import com.example.itquizletspringbootapi.dto.question.QuestionUpdateDto;
import com.example.itquizletspringbootapi.repository.QuestionRepository;
import com.example.itquizletspringbootapi.repository.entity.QuestionEntity;
import com.example.itquizletspringbootapi.repository.entity.QuizEntity;
import com.example.itquizletspringbootapi.service.QuestionService;
import com.example.itquizletspringbootapi.service.mapper.QuestionMapper;
import com.example.itquizletspringbootapi.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final QuestionMapper questionMapper;

    @Override
    public QuestionDto addQuestionToQuiz(UUID quizId, QuestionCreateDto question) {
        QuizEntity quizEntity = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + quizId));
        QuestionEntity questionEntity = questionMapper.toEntity(question);
        questionEntity.setQuiz(quizEntity);
        QuestionEntity savedQuestion = questionRepository.save(questionEntity);
        log.info("Quiz created with ID: {}", savedQuestion.getId());
        return questionMapper.toDTO(questionEntity);
    }

    @Override
    public QuestionDto getQuestionById(UUID questionId) {
        QuestionEntity questionEntity = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with ID: " + questionId));
        log.info("Retrieved question with ID: {}", questionId);
        return questionMapper.toDTO(questionEntity);
    }

    @Override
    public List<QuestionDto> getQuestionsByQuiz(UUID quizId) {
        List<QuestionEntity> questions = questionRepository.findByQuizId(quizId);
        log.info("Retrieved {} questions from quiz with ID: {}", questions.size(), quizId);
        return questions.stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDto updateQuestion(UUID questionId, QuestionUpdateDto updatedQuestionDto) {
        QuestionEntity questionEntity = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with ID: " + questionId));
        questionMapper.updateEntityFromDto(updatedQuestionDto, questionEntity);
        log.info("Updated question with ID: {}", questionId);
        return questionMapper.toDTO(questionEntity);
    }

    @Override
    public void deleteQuestion(UUID questionId) {
        if(!questionRepository.existsById(questionId)) {
            throw new RuntimeException("Question not found with ID: " + questionId);
        }
        questionRepository.deleteById(questionId);
        log.info("Deleted question with ID: {}", questionId);
    }

}
