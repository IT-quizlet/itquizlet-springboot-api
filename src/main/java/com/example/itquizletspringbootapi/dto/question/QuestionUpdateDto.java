package com.example.itquizletspringbootapi.dto.question;

import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerCreateDto;
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
public class QuestionUpdateDto {

    @NotBlank(message = "Question text is mandatory.")
    String text;

    @NotEmpty(message = "Answers list cannot be empty.")
    @Valid
    List<QuestionAnswerCreateDto> answers;

}
