package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.question.QuestionCreateDto;
import com.example.itquizletspringbootapi.dto.question.QuestionDto;
import com.example.itquizletspringbootapi.dto.question.QuestionUpdateDto;
import com.example.itquizletspringbootapi.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/add-question-to-quiz/{quizId}")
    public ResponseEntity<QuestionDto> addQuestionToQuiz(@PathVariable UUID quizId, @RequestBody QuestionCreateDto questionCreateDTO) {
        QuestionDto addedQuestion = questionService.addQuestionToQuiz(quizId, questionCreateDTO);
        return ResponseEntity.ok(addedQuestion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionsById(@PathVariable UUID id) {
        QuestionDto question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/get-questions-by-quiz/{quizId}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByQuiz(@PathVariable UUID quizId) {
        List<QuestionDto> questions = questionService.getQuestionsByQuiz(quizId);
        return ResponseEntity.ok(questions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable UUID id, @RequestBody QuestionUpdateDto questionUpdateDto) {
        QuestionDto updatedQuestion = questionService.updateQuestion(id, questionUpdateDto);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable UUID id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
