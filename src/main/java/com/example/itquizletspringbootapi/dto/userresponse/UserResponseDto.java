package com.example.itquizletspringbootapi.dto.userresponse;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
@Jacksonized
public class UserResponseDto {

    UUID id;
    String username;
    UUID quizId;
    UUID questionId;
    UUID selectedAnswerId;
    LocalDateTime respondedAt;

}
