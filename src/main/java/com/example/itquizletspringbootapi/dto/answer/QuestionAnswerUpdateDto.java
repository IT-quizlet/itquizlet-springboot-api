package com.example.itquizletspringbootapi.dto.answer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class QuestionAnswerUpdateDto {

    @NotEmpty(message = "Answer text is mandatory.")
    String answerText;

    @NotNull(message = "Correctness flag is mandatory.")
    Boolean isCorrect;

}
