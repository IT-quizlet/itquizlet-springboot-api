package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerCreateDto;
import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerDto;
import com.example.itquizletspringbootapi.dto.userresponse.UserResponseDto;
import com.example.itquizletspringbootapi.repository.entity.QuestionAnswerEntity;
import com.example.itquizletspringbootapi.repository.entity.UserEntity;
import com.example.itquizletspringbootapi.repository.entity.UserResponseEntity;
import com.example.itquizletspringbootapi.service.UserResponseService;
import com.example.itquizletspringbootapi.service.mapper.QuestionAnswerMapper;
import com.example.itquizletspringbootapi.service.mapper.UserResponseMapper;
import com.example.itquizletspringbootapi.web.decorators.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class UserResponseController {

    private final UserResponseService userResponseService;
    private final UserResponseMapper userResponseMapper;
    private final QuestionAnswerMapper questionAnswerMapper;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUserResponse(
            @RequestParam UUID quizId,
            @CurrentUser UserEntity user
            ) throws BadRequestException {
        UserResponseEntity userResponse = userResponseService.createUserResponse(user, quizId);
        return ResponseEntity.ok(userResponseMapper.toDto(userResponse));
    }

    @PostMapping("/{responseId}/answers")
    public ResponseEntity<QuestionAnswerDto> addAnswer(
            @PathVariable UUID responseId,
            @RequestParam UUID questionId,
            @RequestBody QuestionAnswerCreateDto answerCreateDto
    ) throws BadRequestException {
        QuestionAnswerEntity answer = userResponseService.addAnswer(responseId, questionId, answerCreateDto.getAnswer());
        return ResponseEntity.ok(questionAnswerMapper.toDto(answer));
    }

    @GetMapping("/by-quiz/{quizId}")
    public ResponseEntity<List<UserResponseDto>> getResponsesByQuiz(@PathVariable UUID quizId) {
        List<UserResponseEntity> responses = userResponseService.getResponsesByQuiz(quizId);
        return ResponseEntity.ok(
                responses.stream()
                        .map(userResponseMapper::toDto)
                        .collect(Collectors.toList())
        );
    }
}
