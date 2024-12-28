package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.question.QuestionCreateDto;
import com.example.itquizletspringbootapi.dto.question.QuestionData;
import com.example.itquizletspringbootapi.dto.question.QuestionDto;
import com.example.itquizletspringbootapi.dto.question.QuestionUpdateDto;
import com.example.itquizletspringbootapi.repository.QuestionRepository;
import com.example.itquizletspringbootapi.repository.entity.QuestionEntity;
import com.example.itquizletspringbootapi.repository.entity.QuizEntity;
import com.example.itquizletspringbootapi.service.QuestionService;
import com.example.itquizletspringbootapi.service.mapper.QuestionMapper;
import com.example.itquizletspringbootapi.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final QuestionMapper questionMapper;

    public void checkIfQuestionBelongsToQuiz(UUID quizId, UUID questionId) throws BadRequestException {
        QuestionEntity question = questionRepository.findById(questionId).
                orElseThrow(() -> new BadRequestException("Question not found with ID: " + questionId));

        if (!question.getQuiz().getId().equals(quizId)) {
            throw new BadRequestException("The question does not belong to the specified quiz.");
        }
    }

    private void checkAnswer(QuestionData question) throws BadRequestException {
        if (!question.getVariants().contains(question.getCorrectAnswer())) {
            throw new BadRequestException("Correct answer must be one of the variants.");
        }
    }

    @Override
    public QuestionDto addQuestionToQuiz(UUID quizId, QuestionCreateDto question) throws BadRequestException {
        checkAnswer(question);

        QuizEntity quizEntity = quizRepository.findById(quizId)
                .orElseThrow(() -> new BadRequestException("Quiz not found with ID: " + quizId));

        QuestionEntity questionEntity = questionMapper.toEntity(question);
        questionEntity.setQuiz(quizEntity);
        questionRepository.save(questionEntity);

        return questionMapper.toDTO(questionEntity);
    }

    @Override
    public QuestionDto getQuestionById(UUID questionId) {
        QuestionEntity questionEntity = questionRepository.findById(questionId).get();

        return questionMapper.toDTO(questionEntity);
    }

    @Override
    public List<QuestionDto> getQuestionsByQuiz(UUID quizId) {
        List<QuestionEntity> questions = questionRepository.findByQuizId(quizId);

        return questions.stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public QuestionDto updateQuestion(UUID questionId, QuestionUpdateDto updatedQuestionDto) throws BadRequestException {
        QuestionEntity questionEntity = questionRepository.findById(questionId).get();
        questionMapper.updateEntityFromDto(updatedQuestionDto, questionEntity);

        checkAnswer(questionEntity);
        questionRepository.save(questionEntity);
        return questionMapper.toDTO(questionEntity);
    }

    @Override
    public void deleteQuestion(UUID questionId) {
        questionRepository.deleteById(questionId);
    }

}
