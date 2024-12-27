package com.example.itquizletspringbootapi.dto.quiz;

import com.example.itquizletspringbootapi.dto.question.QuestionCreateDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class QuizCreateDto {

    @NotBlank(message = "Title is mandatory.")
    String title;

    @NotBlank(message = "Description is mandatory.")
    String description;

    @NotEmpty(message = "Questions list cannot be empty.")
    @Valid
    List<QuestionCreateDto> questions;

}
