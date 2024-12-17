package com.example.itquizletspringbootapi.dto.userresponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Value
@Builder
@Jacksonized
public class UserResponseCreateDto {

    @NotBlank(message = "Username is mandatory.")
    String username;

    @NotNull(message = "Quiz id is mandatory.")
    UUID quizId;

    @NotNull(message = "Question is mandatory.")
    UUID questionId;

    @NotNull(message = "Selected answer id is mandatory.")
    UUID selectedAnswerId;

}
