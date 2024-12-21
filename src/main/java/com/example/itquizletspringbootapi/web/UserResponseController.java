package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.question.QuestionCreateDto;
import com.example.itquizletspringbootapi.dto.question.QuestionDto;
import com.example.itquizletspringbootapi.dto.userresponse.UserResponseCreateDto;
import com.example.itquizletspringbootapi.dto.userresponse.UserResponseDto;
import com.example.itquizletspringbootapi.service.UserResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/user-responses")
@RequiredArgsConstructor
public class UserResponseController {

    private final UserResponseService userResponseService;

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUserResponse(@RequestBody UserResponseCreateDto userResponseCreateDto) {
        UserResponseDto userResponseDto = userResponseService.saveUserResponse(userResponseCreateDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<UserResponseDto>> getResponsesByUsername(@PathVariable String username) {
        List<UserResponseDto> responses = userResponseService.getResponsesByUsername(username);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/by-quiz/{quizId}")
    public ResponseEntity<List<UserResponseDto>> getResponsesByQuiz(@PathVariable UUID quizId) {
        List<UserResponseDto> responses = userResponseService.getResponsesByQuiz(quizId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/check-answer/{answerId}")
    public ResponseEntity<Boolean> checkIfAnswerIsCorrect(@PathVariable UUID answerId) {
        Boolean isCorrect = userResponseService.checkIfAnswerIsCorrect(answerId);
        return ResponseEntity.ok(isCorrect);
    }
}
