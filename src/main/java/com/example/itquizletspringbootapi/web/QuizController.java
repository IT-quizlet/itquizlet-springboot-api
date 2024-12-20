package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.quiz.QuizCreateDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizUpdateDto;
import com.example.itquizletspringbootapi.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.itquizletspringbootapi.dto.quiz.QuizDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor

public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(@RequestBody QuizCreateDto quizDTO) {
        QuizDto createdQuiz = quizService.createQuiz(quizDTO);
        return ResponseEntity.ok(createdQuiz);
    }


    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuizByID(@PathVariable UUID id) {
        QuizDto quiz = quizService.getQuizById(id);
        return ResponseEntity.ok(quiz);
    }
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<QuizDto>> getQuizByOwner(@PathVariable UUID ownerId) {
        List<QuizDto> quizzes = quizService.getQuizzesByOwner(ownerId);
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/")
    public ResponseEntity<List<QuizDto>> getAllQuizzes() {
        List<QuizDto> allQuizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(allQuizzes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizDto> updateQuizById(@PathVariable UUID id, @RequestBody QuizUpdateDto quizUpdateDTO) {
        QuizDto updatedQuiz = quizService.updateQuiz(id, quizUpdateDTO);
        return ResponseEntity.ok(updatedQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizById(@PathVariable UUID id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}
