package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.quiz.QuizDto;
import com.example.itquizletspringbootapi.dto.user.UserDto;
import com.example.itquizletspringbootapi.dto.user.UserUpdateDto;
import com.example.itquizletspringbootapi.repository.QuizRepository;
import com.example.itquizletspringbootapi.repository.SavedQuizzes;
import com.example.itquizletspringbootapi.repository.entity.QuizEntity;
import com.example.itquizletspringbootapi.repository.entity.SavedQuizEntity;
import com.example.itquizletspringbootapi.repository.entity.SavedQuizId;
import com.example.itquizletspringbootapi.repository.entity.UserEntity;
import com.example.itquizletspringbootapi.service.QuizService;
import com.example.itquizletspringbootapi.service.UserService;
import com.example.itquizletspringbootapi.service.mapper.QuizMapper;
import com.example.itquizletspringbootapi.service.mapper.UserMapper;
import com.example.itquizletspringbootapi.web.decorators.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final QuizService quizService;
    private final UserMapper userMapper;
    private final QuizMapper quizMapper;
    private final SavedQuizzes savedQuizzesRepository;
    private final QuizRepository quizRepository;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) throws BadRequestException {
        UserEntity user = userService.getUserById(id);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(
            @RequestBody UserUpdateDto userUpdateDTO,
            @CurrentUser UserEntity user
    ) throws BadRequestException {
        UserEntity updatedUser = userService.updateUser(user.getId(), userUpdateDTO);
        return ResponseEntity.ok(userMapper.toDto(updatedUser));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser(@CurrentUser UserEntity user) throws BadRequestException {
        userService.deleteUser(user.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/quizzes")
    public ResponseEntity<List<QuizDto>> getQuizzesByOwner(@CurrentUser UserEntity user) {
        List<QuizEntity> quizzes = quizService.getQuizzesByOwner(user.getId());
        List<QuizDto> mappedQuizzes = quizzes.stream()
                .map(quizMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mappedQuizzes);
    }

    @PostMapping("/savedQuizees/toggle/{quizId}")
    public ResponseEntity<Void> toggleSavedQuiz(
            @PathVariable UUID quizId,
            @CurrentUser UserEntity user
    ) throws BadRequestException {
        SavedQuizId savedQuizId = new SavedQuizId(user.getId(), quizId);

        if (savedQuizzesRepository.existsById(savedQuizId)) {
            savedQuizzesRepository.deleteById(savedQuizId);
        } else {
            QuizEntity quiz = quizRepository.findById(quizId)
                    .orElseThrow(() -> new BadRequestException("Quiz not found"));

            SavedQuizEntity savedQuiz = SavedQuizEntity.builder()
                    .id(savedQuizId)
                    .user(user)
                    .quiz(quiz)
                    .build();
            savedQuizzesRepository.save(savedQuiz);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/savedQuizees")
    public ResponseEntity<List<QuizDto>> getSavedQuizzes(@CurrentUser UserEntity user) {
        List<SavedQuizEntity> savedQuizzes = savedQuizzesRepository.findAllByIdUserId(user.getId());
        List<QuizDto> mappedQuizzes = savedQuizzes.stream()
                .map(savedQuiz -> quizMapper.toDTO(savedQuiz.getQuiz()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(mappedQuizzes);
    }
}
