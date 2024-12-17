package com.example.itquizletspringbootapi.dto.question;

import com.example.itquizletspringbootapi.dto.answer.QuestionAnswerDto;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Value
@Builder
@Jacksonized
public class QuestionDto {

    UUID id;
    String text;
    List<QuestionAnswerDto> answers;

}
