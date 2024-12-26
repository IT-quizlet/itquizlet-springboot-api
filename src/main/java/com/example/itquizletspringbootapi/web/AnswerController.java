package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerDto;
import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerCreateDto;
import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerUpdateDto;
import com.example.itquizletspringbootapi.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@Validated
@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("{questionId}")
    public ResponseEntity<QuestionAnswerDto> addAnswerToQuestion(@PathVariable UUID questionId, @RequestBody QuestionAnswerCreateDto questionAnswerCreateDto) {
        QuestionAnswerDto questionAnswerDto = answerService.addAnswerToQuestion(questionId, questionAnswerCreateDto);
        return ResponseEntity.ok(questionAnswerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionAnswerDto> getAnswersById(@PathVariable UUID id) {
        QuestionAnswerDto questionAnswerDto = answerService.getAnswerById(id);
        return ResponseEntity.ok(questionAnswerDto);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<QuestionAnswerDto>> getAnswersByQuestion(@PathVariable UUID questionId) {
        List<QuestionAnswerDto> answers = answerService.getAnswersByQuestion(questionId);
        return ResponseEntity.ok(answers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionAnswerDto> updateAnswer(@PathVariable UUID id, @RequestBody QuestionAnswerUpdateDto questionAnswerUpdateDto) {
        QuestionAnswerDto questionAnswerDto = answerService.updateAnswer(id, questionAnswerUpdateDto);
        return ResponseEntity.ok(questionAnswerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable UUID id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}
