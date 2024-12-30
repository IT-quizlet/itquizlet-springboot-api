package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.question.QuestionCreateDto;
import com.example.itquizletspringbootapi.dto.question.QuestionDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizCreateDto;
import com.example.itquizletspringbootapi.dto.quiz.QuizUpdateDto;
import com.example.itquizletspringbootapi.dto.userresponse.UserResponseDto;
import com.example.itquizletspringbootapi.repository.entity.Level;
import com.example.itquizletspringbootapi.repository.entity.QuestionEntity;
import com.example.itquizletspringbootapi.repository.entity.QuizEntity;
import com.example.itquizletspringbootapi.repository.entity.UserEntity;
import com.example.itquizletspringbootapi.service.QuestionService;
import com.example.itquizletspringbootapi.service.QuizService;
import com.example.itquizletspringbootapi.service.mapper.QuestionMapper;
import com.example.itquizletspringbootapi.service.mapper.QuizMapper;
import com.example.itquizletspringbootapi.web.decorators.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

@Validated
@RestController
@RequestMapping("/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;
    private final QuizMapper quizMapper;
    private final QuestionMapper questionMapper;

    @Operation(
            summary = "Create a new quiz",
            description = "Allows an authenticated user to create a new quiz",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quiz successfully created",
                    content = @Content(schema = @Schema(implementation = QuizDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(
            @RequestBody(description = "Details of the quiz to be created", required = true)
            @Valid QuizCreateDto quizCreateDTO,
            @CurrentUser UserEntity user
    ) {
        QuizEntity createdQuiz = quizService.createQuiz(quizCreateDTO, user);
        return ResponseEntity.ok(quizMapper.toDTO(createdQuiz));
    }

    @Operation(
            summary = "Get quiz by ID",
            description = "Retrieve detailed information about a specific quiz by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quiz details retrieved successfully",
                    content = @Content(schema = @Schema(implementation = QuizDto.class))),
            @ApiResponse(responseCode = "404", description = "Quiz not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuizById(@PathVariable UUID id) throws BadRequestException {
        QuizEntity quiz = quizService.getQuizById(id);
        return ResponseEntity.ok(quizMapper.toDTO(quiz));
    }

    @Operation(
            summary = "Get all quizzes",
            description = "Retrieve a list of all quizzes with optional filtering by level and category"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of quizzes retrieved successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
    })
    @GetMapping
    public ResponseEntity<List<QuizDto>> getAllQuizzes(
            @RequestParam(name = "level", required = false) Level level,
            @RequestParam(name = "category", required = false) Optional<String> category
    ) {
        List<QuizDto> allQuizzes = quizService.getAllQuizzes(level, category);
        return ResponseEntity.ok(allQuizzes);
    }

    @Operation(
            summary = "Update a quiz by ID",
            description = "Allows the owner of a quiz to update its details",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quiz updated successfully",
                    content = @Content(schema = @Schema(implementation = QuizDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: User does not own the quiz"),
            @ApiResponse(responseCode = "404", description = "Quiz not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<QuizDto> updateQuizById(@PathVariable UUID id, @RequestBody QuizUpdateDto quizUpdateDTO, @CurrentUser UserEntity user) throws BadRequestException {
        QuizEntity updatedQuiz = quizService.updateQuiz(id, quizUpdateDTO, user.getId());
        return ResponseEntity.ok(quizMapper.toDTO(updatedQuiz));
    }

    @Operation(
            summary = "Delete a quiz by ID",
            description = "Allows the owner of a quiz to delete it",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Quiz successfully deleted"),
            @ApiResponse(responseCode = "403", description = "Forbidden: User does not own the quiz"),
            @ApiResponse(responseCode = "404", description = "Quiz not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizById(@PathVariable UUID id, @CurrentUser UserEntity user) throws BadRequestException {
        quizService.deleteQuiz(id, user.getId());
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Add a question to a quiz",
            description = "Allows the owner of a quiz to add a question to it",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Question added successfully",
                    content = @Content(schema = @Schema(implementation = QuestionDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: User does not own the quiz"),
            @ApiResponse(responseCode = "404", description = "Quiz not found")
    })
    @PostMapping("/{id}/questions")
    public ResponseEntity<QuestionDto> addQuestionToQuiz(
            @PathVariable UUID id,
            @RequestBody(description = "Details of the question to be added", required = true)
            @Valid QuestionCreateDto questionCreateDTO,
            @CurrentUser UserEntity user
    ) throws BadRequestException {
        quizService.checkOwner(id, user.getId());
        QuestionEntity addedQuestion = questionService.addQuestionToQuiz(id, questionCreateDTO);
        return ResponseEntity.ok(questionMapper.toDTO(addedQuestion));
    }
}
