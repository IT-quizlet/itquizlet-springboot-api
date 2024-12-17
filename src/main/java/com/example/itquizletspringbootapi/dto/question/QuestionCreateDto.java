package com.example.itquizletspringbootapi.dto.question;

import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerCreateDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Value
@Builder
@Jacksonized
public class QuestionCreateDto {

    @NotBlank(message = "Question text is mandatory.")
    String text;

    @NotNull(message = "Question id is mandatory.")
    UUID questionId;

    @NotEmpty(message = "Answers list cannot be empty.")
    @Valid
    List<QuestionAnswerCreateDto> answers;

}
