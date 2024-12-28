package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.question.QuestionCreateDto;
import com.example.itquizletspringbootapi.dto.question.QuestionDto;
import com.example.itquizletspringbootapi.dto.question.QuestionUpdateDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizCreateDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizUpdateDto;
import com.example.itquizletspringbootapi.repository.entity.Level;
import com.example.itquizletspringbootapi.repository.entity.QuestionEntity;
import com.example.itquizletspringbootapi.repository.entity.QuizEntity;
import com.example.itquizletspringbootapi.repository.entity.UserEntity;
import com.example.itquizletspringbootapi.service.QuestionService;
import com.example.itquizletspringbootapi.service.QuizService;
import com.example.itquizletspringbootapi.service.mapper.QuestionMapper;
import com.example.itquizletspringbootapi.service.mapper.QuizMapper;
import com.example.itquizletspringbootapi.web.decorators.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.itquizletspringbootapi.dto.quiz.QuizDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/quizzes")
@RequiredArgsConstructor

public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;
    private final QuizMapper quizMapper;
    private final QuestionMapper questionMapper;

    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(
            @RequestBody @Valid QuizCreateDto quizCreateDTO,
            @CurrentUser UserEntity user
            ) {
        QuizEntity createdQuiz = quizService.createQuiz(quizCreateDTO, user);
        return ResponseEntity.ok(quizMapper.toDTO(createdQuiz));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuizById(@PathVariable UUID id) throws BadRequestException {
        QuizEntity quiz = quizService.getQuizById(id);
        return ResponseEntity.ok(quizMapper.toDTO(quiz));
    }

    @GetMapping
    public ResponseEntity<List<QuizDto>> getAllQuizzes(
            @RequestParam(name = "level", required = false) Level level,
            @RequestParam(name = "category", required = false) Optional<String> category
    ) {
        List<QuizDto> allQuizzes = quizService.getAllQuizzes(level, category);
        return ResponseEntity.ok(allQuizzes);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuizDto> updateQuizById(@PathVariable UUID id, @RequestBody QuizUpdateDto quizUpdateDTO, @CurrentUser UserEntity user) throws BadRequestException {
        QuizEntity updatedQuiz = quizService.updateQuiz(id, quizUpdateDTO, user.getId());
        return ResponseEntity.ok(quizMapper.toDTO(updatedQuiz));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizById(@PathVariable UUID id, @CurrentUser UserEntity user) throws BadRequestException {
        quizService.deleteQuiz(id, user.getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/questions")
    public ResponseEntity<QuestionDto> addQuestionToQuiz(
            @PathVariable UUID id,
            @RequestBody QuestionCreateDto questionCreateDTO,
            @CurrentUser UserEntity user
    ) throws BadRequestException {
        quizService.checkOwner(id, user.getId());
        QuestionEntity addedQuestion = questionService.addQuestionToQuiz(id, questionCreateDTO);
        return ResponseEntity.ok(questionMapper.toDTO(addedQuestion));
    }

    @GetMapping("{id}/questions")
    public ResponseEntity<List<QuestionDto>> getQuestionsByQuiz(@PathVariable UUID id) {
        List<QuestionEntity> questions = questionService.getQuestionsByQuiz(id);
        return ResponseEntity.ok(
                questions.stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}/questions/{questionId}")
    public ResponseEntity<QuestionDto> getQuestionById(
            @PathVariable UUID id,
            @PathVariable UUID questionId
    ) throws BadRequestException {
        questionService.checkIfQuestionBelongsToQuiz(id, questionId);
        QuestionEntity question = questionService.getQuestionById(questionId);
        return ResponseEntity.ok(questionMapper.toDTO(question));
    }

    @PatchMapping("/{id}/questions/{questionId}")
    public ResponseEntity<QuestionDto> updateQuestion(
            @PathVariable UUID id,
            @PathVariable UUID questionId,
            @RequestBody QuestionUpdateDto questionUpdateDto,
            @CurrentUser UserEntity user
    ) throws BadRequestException {
        quizService.checkOwner(id, user.getId());
        questionService.checkIfQuestionBelongsToQuiz(id, questionId);
        QuestionEntity updatedQuestion = questionService.updateQuestion(questionId, questionUpdateDto);
        return ResponseEntity.ok(questionMapper.toDTO(updatedQuestion));
    }

    @DeleteMapping("/{id}/questions/{questionId}")
    public ResponseEntity<Void> deleteQuestion(
            @PathVariable UUID id,
            @PathVariable UUID questionId,
            @CurrentUser UserEntity user
    ) throws BadRequestException {
        quizService.checkOwner(id, user.getId());
        questionService.checkIfQuestionBelongsToQuiz(id, questionId);
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }
}
