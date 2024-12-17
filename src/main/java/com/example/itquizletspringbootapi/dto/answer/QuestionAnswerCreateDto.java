package com.example.itquizletspringbootapi.dto.answer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Value
@Builder
@Jacksonized
public class QuestionAnswerCreateDto {

    @NotEmpty(message = "Answer text is mandatory.")
    String answerText;

    @NotNull(message = "Correctness flag is mandatory.")
    Boolean isCorrect;

    @NotNull(message = "Question ID is mandatory.")
    UUID questionId;

}
