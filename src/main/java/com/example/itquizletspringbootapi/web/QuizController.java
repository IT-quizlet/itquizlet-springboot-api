package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.quiz.QuizCreateDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizUpdateDto;
import com.example.itquizletspringbootapi.repository.entity.Level;
import com.example.itquizletspringbootapi.repository.entity.QuizEntity;
import com.example.itquizletspringbootapi.repository.entity.UserEntity;
import com.example.itquizletspringbootapi.service.QuizService;
import com.example.itquizletspringbootapi.service.mapper.QuizMapper;
import com.example.itquizletspringbootapi.web.decorators.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.itquizletspringbootapi.dto.quiz.QuizDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/quizzes")
@RequiredArgsConstructor

public class QuizController {

    private final QuizService quizService;
    private final QuizMapper quizMapper;

    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(
            @RequestBody QuizCreateDto quizCreateDTO,
            @CurrentUser UserEntity user
            ) {
        QuizEntity createdQuiz = quizService.createQuiz(quizCreateDTO, user);
        return ResponseEntity.ok(quizMapper.toDTO(createdQuiz));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuizById(@PathVariable UUID id) {
        QuizEntity quiz = quizService.getQuizById(id);
        return ResponseEntity.ok(quizMapper.toDTO(quiz));
    }

    @GetMapping("/owner")
    public ResponseEntity<List<QuizDto>> getQuizzesByOwner(@CurrentUser UserEntity user) {
        List<QuizEntity> quizzes = quizService.getQuizzesByOwner(user.getId());
        List<QuizDto> mappedQuizzes = quizzes.stream()
                .map(quizMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mappedQuizzes);
    }

    @GetMapping
    public ResponseEntity<List<QuizDto>> getAllQuizzes(
            @RequestParam(required = false) Level level,
            @RequestParam(required = false) List<String> categories
            ) {
        List<QuizDto> allQuizzes = quizService.getAllQuizzes(level, categories);
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
}
